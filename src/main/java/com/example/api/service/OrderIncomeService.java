package com.example.api.service;

import com.example.api.entity.bo.MatchMemberBo;
import com.example.api.entity.bo.MerchantConfigBo;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.RequestCallBackBo;
import java.math.BigDecimal;
import org.springframework.remoting.RemoteAccessException;

public interface OrderIncomeService {

  void checkParam(OrderIncomeBo orderIncomeBo, MerchantConfigBo merchantConfigBo);

  /**
   * 订单通知 请求会调处理
   * @param requestCallBackBo
   */
  void processCallBackRecharge(RequestCallBackBo requestCallBackBo) throws RemoteAccessException;

  boolean updateOrderInMatch(String orderId, MatchMemberBo ret);
}
