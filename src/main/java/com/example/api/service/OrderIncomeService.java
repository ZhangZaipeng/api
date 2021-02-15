package com.example.api.service;

import com.example.api.controller.OrderInVo;
import com.example.api.entity.bo.MatchMemberBo;
import com.example.api.entity.bo.MerchantConfigBo;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.RequestCallBackBo;
import java.math.BigDecimal;
import org.springframework.remoting.RemoteAccessException;

public interface OrderIncomeService {

  void checkParam(OrderIncomeBo orderIncomeBo, MerchantConfigBo merchantConfigBo);

  OrderInVo selectOrderInByToken(String token);
}
