package com.example.api.service.impl.unifiedorder;

import com.example.api.common.bean.RedisUtils;
import com.example.api.common.constant.OrderTypeEnum;
import com.example.api.common.constant.PayConstants;
import com.example.api.common.exception.ResultErrException;
import com.example.api.common.response.ResponseCode;
import com.example.api.common.tools.Conv;
import com.example.api.common.tools.Id;
import com.example.api.common.tools.PayUtil;
import com.example.api.common.tools.RandomUtils;
import com.example.api.common.tools.YvanUtil;
import com.example.api.entity.OrderIn;
import com.example.api.entity.SystemConfig;
import com.example.api.entity.bo.MatchMemberBo;
import com.example.api.entity.bo.MerchantConfigBo;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.PayUnifiedorderDto;
import com.example.api.mapper.MemberReceivablesMapper;
import com.example.api.mapper.MerchantConfigMapper;
import com.example.api.mapper.OrderInMapper;
import com.example.api.mapper.SystemConfigMapper;
import com.example.api.service.IdCreatorService;
import com.example.api.service.OrderIncomeService;
import com.example.api.service.impl.unifiedorder.algorithm.MatchAlgorithm;
import com.example.api.service.impl.unifiedorder.algorithm.MatchWeightRandom;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
public class AliToBankUnifiedorder implements IncomeUnifiedorder, InitializingBean {

  @Autowired
  private MerchantConfigMapper merchantConfigMapper;

  @Autowired
  private OrderIncomeService orderIncomeService;

  @Autowired
  private OrderInMapper orderInMapper;

  @Autowired
  private IdCreatorService idCreatorService;

  @Autowired
  private SystemConfigMapper systemConfigMapper;

  @Autowired
  private MemberReceivablesMapper memberReceivablesMapper;

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public PayUnifiedorderDto unifiedorder(OrderIncomeBo orderIncomeBo) {

    log.info("unifiedorder ---> orderIncomeBo : " + YvanUtil.toJson(orderIncomeBo));
    String appId = orderIncomeBo.getAppId();
    String merchantId = orderIncomeBo.getMerchantId(); // 商户ID
    String outTradeNo = orderIncomeBo.getOutTradeNo(); // 商户订单号
    String orderType = orderIncomeBo.getOrderType();

    // 查询商户信息
    MerchantConfigBo merchantConfigBo =
        merchantConfigMapper.selectMerchantConfigBoByMerchantAndAppId(merchantId, appId, orderType);

    // 签名校验
    orderIncomeService.checkParam(orderIncomeBo, merchantConfigBo);

    // 校验商户订单号
    OrderIn orderIn = orderInMapper.selectByOutOrderIdAndMerchantId(merchantId, outTradeNo);

    if (orderIn != null) {
      throw new ResultErrException("商户订单号已经存在");
    }

    // 下单
    OrderIn inOrderIn = new OrderIn();
    long orderTime = System.currentTimeMillis();
    String orderId = idCreatorService.nextValue(OrderTypeEnum.getEnumByOrderType(orderType), orderTime);
    inOrderIn.setOrderId(orderId);

    // 订单 token 加密
    String encodeToken = PayUtil.desEncode(orderId);

    inOrderIn.setOrderToken(encodeToken);
    inOrderIn.setAppId(appId);
    inOrderIn.setMerchantId(Conv.NL(merchantId));

    // 商户订单号
    inOrderIn.setOutTradeNo(outTradeNo);
    inOrderIn.setMerchantUserId(orderIncomeBo.getMerchantUserId());
    // 产生口令
    inOrderIn.setNonceStr(RandomUtils.randomStr(4));

    BigDecimal orderAmt = Conv.NDec(orderIncomeBo.getAmount()).setScale(0, BigDecimal.ROUND_UP);
    inOrderIn.setOrderAmt(orderAmt);

    BigDecimal discount = RandomUtils.discount();
    inOrderIn.setPayAmt(orderAmt.subtract(discount));

    inOrderIn.setOrderType(Conv.NShort(orderType));

    // 回调地址
    inOrderIn.setCallbackNotifyUrl(orderIncomeBo.getNotifyUrl());

    inOrderIn.setOrderPlatformCommission(merchantConfigBo.getPlatformCommission());
    inOrderIn.setOrderTeamCommission(merchantConfigBo.getTeamCommossion());
    inOrderIn.setOrderCashierCommission(merchantConfigBo.getCashierCommossion());

    int r = orderInMapper.insertSelective(inOrderIn);

    if (r <= 0) {
      throw new ResultErrException(ResponseCode.FAIL,"下单失败");
    }

    // 过期标识 前缀 + orderId
    RedisUtils.setex(PayConstants.RECHARGE_ORDER_TIME_OUT_FLAG + orderId,
        PayConstants.PAY_TIME_OUT * 60, orderId);
    // 匹配中标识 前缀 + orderId
    RedisUtils.setex(PayConstants.RECHARGE_ORDER_MATCH_FLAG + orderId,
        PayConstants.PAY_TIME_OUT * 60, orderId);
    // 支付中标识 前缀 + orderId
    RedisUtils.setex(PayConstants.RECHARGE_ORDER_PAYING_FLAG + orderId,
        PayConstants.PAY_TIME_OUT * 60, orderId);

    // 开启匹配承兑商
    MatchOrderDBTask matchOrderDBTask = new MatchOrderDBTask(orderId, orderType, orderAmt, this);
    ThreadExecutorUtils.executeTask(matchOrderDBTask);

    PayUnifiedorderDto payUnifiedorderDto = new PayUnifiedorderDto();
    payUnifiedorderDto.setOrderId(orderId);

    SystemConfig systemConfig = systemConfigMapper.selectByPrimaryKey("payment_domain");

    payUnifiedorderDto.setAccesUrl(systemConfig.getRemark1() + systemConfig.getRemark2() + "?token=" + encodeToken);
    log.info("unifiedorder --> accesUrl :  " + YvanUtil.toJson(payUnifiedorderDto));

    return payUnifiedorderDto;
  }

  @Override
  public MatchMemberBo nextDBMember(String payType, BigDecimal unitAmt) {

    List<MatchMemberBo> matchMemberBoList = memberReceivablesMapper.selectMatchMemberList(payType, unitAmt);

    if (CollectionUtils.isEmpty(matchMemberBoList)) {
      return null;
    }

    MatchAlgorithm matchAlgorithm = new MatchWeightRandom();

    MatchMemberBo match = matchAlgorithm.get(matchMemberBoList);

    return match;
  }

  @Override
  public boolean updateOrderInMatchSuccess(String orderId, MatchMemberBo ret) {

    // 更新 订单状态  收款人
    int r = orderInMapper.updateOrderDistributionInfo(orderId, ret);

    if (r <= 0) {
      throw new ResultErrException("updateRechargeOrder + 更新订单状态失败");
    }

    // 删除匹配中表示
    RedisUtils.del(PayConstants.RECHARGE_ORDER_MATCH_FLAG + orderId);

    return true;
  }

  @Override
  public void afterPropertiesSet() {
    PayIncomeFactory.registerPayService(OrderTypeEnum.Ali_To_Bank, this);
  }

}
