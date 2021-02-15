package com.example.api.controller;

import lombok.Data;

@Data
public class OrderInVo {

  public String orderId;
  public String payAmt;

  private String realName;
  private String account;
  private String address;
}
