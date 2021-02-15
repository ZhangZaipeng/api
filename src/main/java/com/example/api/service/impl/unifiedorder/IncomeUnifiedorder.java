package com.example.api.service.impl.unifiedorder;

import com.example.api.entity.bo.MatchMemberBo;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.PayUnifiedorderDto;
import java.math.BigDecimal;

public interface IncomeUnifiedorder {

  PayUnifiedorderDto unifiedorder(OrderIncomeBo orderIncomeBo);

  MatchMemberBo nextDBMember(String payType, BigDecimal unitAmt);

  boolean updateOrderInMatchSuccess(String orderId, MatchMemberBo ret);
}
