package com.example.api.common.constant;

public class PayConstants {

  // 订单超时时间 分钟
  public static final int PAY_TIME_OUT = 30;

  // 充值订单过期标识
  public static final String RECHARGE_ORDER_TIME_OUT_FLAG = "ORDER:RECHARGE:TIMEOUT:FLAG:";
  // 充值订单 匹配中标识
  public static final String RECHARGE_ORDER_MATCH_FLAG = "ORDER:RECHARGE:MATCH:FLAG:";
  // 充值订单 付款中标识
  public static final String RECHARGE_ORDER_PAYING_FLAG = "ORDER:RECHARGE:PAYING:FLAG:";

  // 订单 序列号
  public static final String TRADDE_ORDER = "ORDER:";

  // 收款员池
  public static final String PAY_ORDER_POLL = "ORDER:POLL:";

  public static int SMS_EXPIRE_SEC = 120;

}
