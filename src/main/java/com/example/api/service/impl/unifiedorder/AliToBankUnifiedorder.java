package com.example.api.service.impl.unifiedorder;

import com.example.api.common.constant.OrderTypeEnum;
import com.example.api.common.tools.Conv;
import com.example.api.common.tools.Id;
import com.example.api.common.tools.PayUtil;
import com.example.api.common.tools.RandomUtils;
import com.example.api.common.tools.YvanUtil;
import com.example.api.entity.OrderIn;
import com.example.api.entity.bo.MerchantConfigBo;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.PayUnifiedorderDto;
import com.example.api.mapper.MerchantConfigMapper;
import com.example.api.mapper.OrderInMapper;
import com.example.api.service.IdCreatorService;
import com.example.api.service.OrderIncomeService;
import java.math.BigDecimal;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  @Override
  public PayUnifiedorderDto unifiedorder(OrderIncomeBo orderIncomeBo) {

    log.info("unifiedorder ---> orderIncomeBo : " + YvanUtil.toJson(orderIncomeBo));
    String appId = orderIncomeBo.getAppId();
    String merchantId = orderIncomeBo.getMerchantId(); // 商户ID
    String outTradeNo = orderIncomeBo.getOutTradeNo(); // 商户订单号
    String orderType = orderIncomeBo.getOrderTypeEnum().getOrderType();

    // 查询商户信息
    MerchantConfigBo MerchantConfigBo =
        merchantConfigMapper.selectMerchantConfigBoByMerchantAndAppId(merchantId, appId, orderType);

    // 签名校验
    orderIncomeService.checkParam(orderIncomeBo, MerchantConfigBo);

    // 校验商户订单号
    OrderIn orderIn = orderInMapper.selectByOutOrderIdAndMerchantId(merchantId, outTradeNo);

    // 下单

    OrderIn inOrderIn = new OrderIn();
    long orderTime = System.currentTimeMillis();
    String orderId = idCreatorService.nextValue(orderIncomeBo.getOrderTypeEnum(), orderTime);
    inOrderIn.setOrderId(orderId);

    // 订单 token 加密
    String encodeToken = PayUtil.desEncode(orderId);

    inOrderIn.setOrderToken(encodeToken);
    inOrderIn.setAppId(appId);
    inOrderIn.setMerchantId(Conv.NL(merchantId));

    // 商户订单号
    inOrderIn.setOutTradeNo(outTradeNo);
    // 产生口令
    inOrderIn.setNonceStr(RandomUtils.randomStr(4));

    BigDecimal orderAmt = Conv.NDec(orderIncomeBo.getAmount()).setScale(2, BigDecimal.ROUND_UP);
    inOrderIn.setOrderAmt(orderAmt);

    BigDecimal discount = RandomUtils.discount();
    inOrderIn.setPayAmt(orderAmt.subtract(discount));

    inOrderIn.setOrderType(Conv.NShort(orderType));

    // 回调地址
    inOrderIn.setNotifyUrl(orderIncomeBo.getNotifyUrl());

    PayUnifiedorderDto payUnifiedorderDto = new PayUnifiedorderDto();

    return payUnifiedorderDto;
  }

  @Override
  public void afterPropertiesSet() {
    PayIncomeFactory.registerPayService(OrderTypeEnum.Ali_To_Bank, this);
  }
}
