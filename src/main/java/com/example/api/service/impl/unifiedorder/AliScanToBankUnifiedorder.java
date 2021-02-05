package com.example.api.service.impl.unifiedorder;

import com.example.api.common.constant.OrderTypeEnum;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.PayUnifiedorderDto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class AliScanToBankUnifiedorder implements IncomeUnifiedorder, InitializingBean {

  @Override
  public PayUnifiedorderDto unifiedorder(OrderIncomeBo orderIncomeBo) {

    return null;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    PayIncomeFactory.registerPayService(OrderTypeEnum.Ali_Scan_To_Bank, this);
  }

}
