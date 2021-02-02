package com.example.api.service;

import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.PayUnifiedorderDto;

public interface IncomeUnifiedorder {

  PayUnifiedorderDto unifiedorder(OrderIncomeBo orderIncomeBo);

}
