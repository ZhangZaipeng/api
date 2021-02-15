package com.example.api.service.impl;

import com.example.api.common.exception.ResultErrException;
import com.example.api.common.response.ResponseCode;
import com.example.api.common.tools.Conv;
import com.example.api.common.tools.PayUtil;
import com.example.api.common.tools.YvanUtil;
import com.example.api.entity.bo.MatchMemberBo;
import com.example.api.entity.bo.MerchantConfigBo;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.RequestCallBackBo;
import com.example.api.service.OrderIncomeService;
import java.math.BigDecimal;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderIncomeServiceImpl implements OrderIncomeService {

  @Value("${payment.signOpen}")
  private boolean signOpen;

  @Override
  public void checkParam(OrderIncomeBo orderIncomeBo, MerchantConfigBo merchantConfigBo) {
    // 校验参数
    if (null == merchantConfigBo) {
      throw new ResultErrException("应用ID 不存在");
    }

    String appSecret = merchantConfigBo.getAppSecret();
    String sign = orderIncomeBo.getSign();
    log.info("unifiedorder ---> sign : " + sign);

    Map<String, String> map = YvanUtil.bean2Map(orderIncomeBo);

    String systemSign = PayUtil.generateSignature(map, appSecret);
    log.info("unifiedorder ---> systemSign : " + systemSign);

    if (signOpen) { // 是否开启签名
      if (!sign.equals(systemSign)) { // 签名校验
        log.info("unifiedorder ---> 签名校验 错误");
        throw new ResultErrException("签名不正确");
      }
    }

    // 交易金额
    BigDecimal amount = Conv.NDec(orderIncomeBo.getAmount());
    if (amount.compareTo(BigDecimal.ZERO) == 0) {
      throw new ResultErrException("交易金额参数异常");
    }

    // 交易金额精度
    if (amount.stripTrailingZeros().scale() > 0) {
      throw new ResultErrException("交易精度最多为个位");
    }

    if (merchantConfigBo.getMrId() == null) {
      throw new ResultErrException("订单类型不支持");
    }
  }

  @Override
  public void processCallBackRecharge(RequestCallBackBo requestCallBackBo)
      throws RemoteAccessException {

  }

  @Override
  public boolean updateOrderInMatch(String orderId, MatchMemberBo ret) {
    return false;
  }
}
