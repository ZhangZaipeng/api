package com.example.api.service;

import com.example.api.common.constant.OrderTypeEnum;
import com.example.api.common.tools.Id;
import com.example.api.common.tools.YvanUtil;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.PayUnifiedorderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class AliToBankUnifiedorder implements IncomeUnifiedorder, InitializingBean {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public PayUnifiedorderDto unifiedorder(OrderIncomeBo orderIncomeBo) {

    logger.info("unifiedorder ---> payRechargeBo : " + YvanUtil.toJson(orderIncomeBo));
    String appId = orderIncomeBo.getAppId();
    String merchantId = orderIncomeBo.getMerchantId(); // 商户ID
    String outTradeNo = orderIncomeBo.getOutTradeNo(); // 商户订单号

    // 查询商户信息

    // 签名校验

    // 校验商户订单号

    // 下单
    Id.newInstance(1);
    long id = Id.nextId();

    PayUnifiedorderDto payUnifiedorderDto = new PayUnifiedorderDto();

    return payUnifiedorderDto;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    PayIncomeFactory.registerPayService(OrderTypeEnum.Ali_To_Bank, this);
  }
}
