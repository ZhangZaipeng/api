package com.example.api.common.constant;

public enum OrderTypeEnum {

  Ali_Scan_To_Bank("支付宝扫码转卡","IN", "1"),
  Ali_To_Bank("支付宝复制转卡", "IN","2"),
  ;

  private String desc;
  private String orderFlag;
  private String orderType;

  OrderTypeEnum(String desc, String orderFlag, String orderType) {
    this.desc = desc;
    this.orderFlag = orderFlag;
    this.orderType = orderType;
  }

  public String getDesc() {
    return desc;
  }

  public String getOrderType() {
    return orderType;
  }

  public String getOrderFlag() {
    return orderFlag;
  }

  public static OrderTypeEnum getEnumByOrderType(String orderType) {
    for (OrderTypeEnum orderTypeEnum : OrderTypeEnum.values()) {
      if (orderTypeEnum.orderType.equals(orderType)) {
        return orderTypeEnum;
      }
    }
    return null;
  }

}
