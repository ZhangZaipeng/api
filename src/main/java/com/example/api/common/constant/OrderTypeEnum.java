package com.example.api.common.constant;

public enum OrderTypeEnum {

  Ali_Scan_To_Bank("支付宝扫码转卡", "1"),
  Ali_To_Bank("支付宝复制转卡", "2"),
  ;

  private String desc;
  private String orderType;

  OrderTypeEnum(String desc, String orderType) {
    this.desc = desc;
    this.orderType = orderType;
  }

  public String getDesc() {
    return desc;
  }

  public String getOrderType() {
    return orderType;
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
