package com.example.api.service.impl;

import com.example.api.common.exception.ResultErrException;
import com.example.api.common.response.ResponseCode;
import com.example.api.common.tools.Conv;
import com.example.api.common.tools.PayUtil;
import com.example.api.common.tools.StringUtils;
import com.example.api.common.tools.YvanUtil;
import com.example.api.controller.OrderInVo;
import com.example.api.entity.OrderIn;
import com.example.api.entity.bo.MatchMemberBo;
import com.example.api.entity.bo.MerchantConfigBo;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.RequestCallBackBo;
import com.example.api.mapper.OrderInMapper;
import com.example.api.service.OrderIncomeService;
import java.math.BigDecimal;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderIncomeServiceImpl implements OrderIncomeService {

  @Value("${payment.signOpen}")
  private boolean signOpen;

  @Autowired
  private OrderInMapper orderInMapper;

  @Override
  public void checkParam(OrderIncomeBo orderIncomeBo, MerchantConfigBo merchantConfigBo) {
    // 校验参数
    if (null == merchantConfigBo) {
      throw new ResultErrException("应用ID 不存在");
    }

    String appSecret = merchantConfigBo.getAppSecret();
    String sign = orderIncomeBo.getSign();
    log.info("unifiedorder ---> sign : " + sign);

    Map<String, String> map = YvanUtil.bean2Map(orderIncomeBo);

    String systemSign = PayUtil.generateSignature(map, appSecret);
    log.info("unifiedorder ---> systemSign : " + systemSign);

    if (signOpen) { // 是否开启签名
      if (!sign.equals(systemSign)) { // 签名校验
        log.info("unifiedorder ---> 签名校验 错误");
        throw new ResultErrException("签名不正确");
      }
    }

    // 交易金额
    BigDecimal amount = Conv.NDec(orderIncomeBo.getAmount());
    if (amount.compareTo(BigDecimal.ZERO) == 0) {
      throw new ResultErrException("交易金额参数异常");
    }

    // 交易金额精度
    if (amount.stripTrailingZeros().scale() > 0) {
      throw new ResultErrException("交易精度最多为个位");
    }

    if (merchantConfigBo.getMrId() == null) {
      throw new ResultErrException("订单类型不支持");
    }
  }

  @Override
  public OrderInVo selectOrderInByToken(String token) {

    // 查出订单
    String orderId = PayUtil.desDecode(token);
    if (StringUtils.isEmpty(orderId)) {
      return null;
    }


    OrderIn orderIn = orderInMapper.selectByPrimaryKey(orderId);
    if (orderIn == null) {
      return null;
    }

    OrderInVo orderInVo = new OrderInVo();
    orderInVo.setOrderId(orderIn.getOrderId());
    orderInVo.setPayAmt(orderIn.getPayAmt().stripTrailingZeros().toPlainString());
    orderInVo.setRealName(orderIn.getReceiveRealName());
    orderInVo.setAccount(orderIn.getReceiveAccount());
    orderInVo.setAddress(orderIn.getReceiveUrl());

    return orderInVo;
  }
}
