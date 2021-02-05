package com.example.api.service.impl.unifiedorder;

import com.example.api.common.constant.OrderTypeEnum;
import com.example.api.common.tools.Id;
import com.example.api.common.tools.YvanUtil;
import com.example.api.entity.bo.MerchantConfigBo;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.PayUnifiedorderDto;
import com.example.api.mapper.MerchantConfigMapper;
import com.example.api.service.OrderIncomeService;
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
    // orderIncomeService.checkParam();


    // 校验商户订单号

    // 下单
    Id.newInstance(1);
    long id = Id.nextId();

    PayUnifiedorderDto payUnifiedorderDto = new PayUnifiedorderDto();

    return payUnifiedorderDto;
  }

  @Override
  public void afterPropertiesSet() {
    PayIncomeFactory.registerPayService(OrderTypeEnum.Ali_To_Bank, this);
  }
}
