package com.example.api.entity.bo;

public class RequestCallBackBo {

  // 订单号
  private String orderId;

  // 返回结果
  private String result;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
