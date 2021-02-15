package com.example.api.service.impl.unifiedorder;

import com.example.api.common.constant.OrderTypeEnum;
import com.example.api.entity.bo.MatchMemberBo;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.PayUnifiedorderDto;
import java.math.BigDecimal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class AliScanToBankUnifiedorder implements IncomeUnifiedorder, InitializingBean {

  @Override
  public void afterPropertiesSet() throws Exception {
    PayIncomeFactory.registerPayService(OrderTypeEnum.Ali_Scan_To_Bank, this);
  }

  @Override
  public PayUnifiedorderDto unifiedorder(OrderIncomeBo orderIncomeBo) {
    return null;
  }

  @Override
  public MatchMemberBo nextDBMember(String payType, BigDecimal unitAmt) {
    return null;
  }

  @Override
  public boolean updateOrderInMatchSuccess(String orderId, MatchMemberBo ret) {
    return false;
  }
}
