package com.example.api.service;

import com.example.api.entity.bo.MerchantConfigBo;
import com.example.api.entity.bo.OrderIncomeBo;

public interface OrderIncomeService {

  void checkParam(OrderIncomeBo orderIncomeBo, MerchantConfigBo merchantConfigBo);
}
