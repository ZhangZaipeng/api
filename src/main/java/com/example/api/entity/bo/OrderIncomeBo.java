package com.example.api.entity.bo;

import com.example.api.common.constant.OrderTypeEnum;
import java.math.BigDecimal;

public class OrderIncomeBo {
  // 应用ID",
  private String appId;
  // 商户号
  private String merchantId;
  // 商户订单号",
  private String outTradeNo;
  // 随机字符串",
  private String nonceStr;
  // 回调地址
  private String notifyUrl;
  // 下单数量（交易币种）",
  private BigDecimal amount;
  // 要求支付类型,
  private OrderTypeEnum orderTypeEnum;
  private String remark;
  // 签名（HMAC-SHA256）",
  private String sign;

  public OrderIncomeBo(String appId, String merchantId, String outTradeNo, String nonceStr,
      String notifyUrl, BigDecimal amount,
      OrderTypeEnum orderTypeEnum, String remark, String sign) {
    this.appId = appId;
    this.merchantId = merchantId;
    this.outTradeNo = outTradeNo;
    this.nonceStr = nonceStr;
    this.notifyUrl = notifyUrl;
    this.amount = amount;
    this.orderTypeEnum = orderTypeEnum;
    this.remark = remark;
    this.sign = sign;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public OrderTypeEnum getOrderTypeEnum() {
    return orderTypeEnum;
  }

  public void setOrderTypeEnum(OrderTypeEnum orderTypeEnum) {
    this.orderTypeEnum = orderTypeEnum;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }
}
