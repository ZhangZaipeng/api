package com.example.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderIn implements Serializable {
    private String orderId;

    private String orderToken;

    private String appId;

    private Long merchantId;

    private Long memberId;

    private String outTradeNo;

    private String nonceStr;

    private Short orderType;

    private BigDecimal orderAmt;

    private BigDecimal payAmt;

    private String notifyUrl;

    private BigDecimal orderPlatformCommission;

    private BigDecimal orderTeamCommission;

    private BigDecimal orderCashierCommission;

    private Date orderAllocationTime;

    private Date orderConfirmTime;

    private Long confirmOperatorId;

    private String receiveAccount;

    private String receiveRealName;

    private String receiveUrl;

    private String callbackNotifyUrl;

    private Short status;

    private String remark;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public Short getOrderType() {
        return orderType;
    }

    public void setOrderType(Short orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public BigDecimal getOrderPlatformCommission() {
        return orderPlatformCommission;
    }

    public void setOrderPlatformCommission(BigDecimal orderPlatformCommission) {
        this.orderPlatformCommission = orderPlatformCommission;
    }

    public BigDecimal getOrderTeamCommission() {
        return orderTeamCommission;
    }

    public void setOrderTeamCommission(BigDecimal orderTeamCommission) {
        this.orderTeamCommission = orderTeamCommission;
    }

    public BigDecimal getOrderCashierCommission() {
        return orderCashierCommission;
    }

    public void setOrderCashierCommission(BigDecimal orderCashierCommission) {
        this.orderCashierCommission = orderCashierCommission;
    }

    public Date getOrderAllocationTime() {
        return orderAllocationTime;
    }

    public void setOrderAllocationTime(Date orderAllocationTime) {
        this.orderAllocationTime = orderAllocationTime;
    }

    public Date getOrderConfirmTime() {
        return orderConfirmTime;
    }

    public void setOrderConfirmTime(Date orderConfirmTime) {
        this.orderConfirmTime = orderConfirmTime;
    }

    public Long getConfirmOperatorId() {
        return confirmOperatorId;
    }

    public void setConfirmOperatorId(Long confirmOperatorId) {
        this.confirmOperatorId = confirmOperatorId;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public String getReceiveRealName() {
        return receiveRealName;
    }

    public void setReceiveRealName(String receiveRealName) {
        this.receiveRealName = receiveRealName;
    }

    public String getReceiveUrl() {
        return receiveUrl;
    }

    public void setReceiveUrl(String receiveUrl) {
        this.receiveUrl = receiveUrl;
    }

    public String getCallbackNotifyUrl() {
        return callbackNotifyUrl;
    }

    public void setCallbackNotifyUrl(String callbackNotifyUrl) {
        this.callbackNotifyUrl = callbackNotifyUrl;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderIn other = (OrderIn) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderToken() == null ? other.getOrderToken() == null : this.getOrderToken().equals(other.getOrderToken()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getNonceStr() == null ? other.getNonceStr() == null : this.getNonceStr().equals(other.getNonceStr()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getOrderAmt() == null ? other.getOrderAmt() == null : this.getOrderAmt().equals(other.getOrderAmt()))
            && (this.getPayAmt() == null ? other.getPayAmt() == null : this.getPayAmt().equals(other.getPayAmt()))
            && (this.getNotifyUrl() == null ? other.getNotifyUrl() == null : this.getNotifyUrl().equals(other.getNotifyUrl()))
            && (this.getOrderPlatformCommission() == null ? other.getOrderPlatformCommission() == null : this.getOrderPlatformCommission().equals(other.getOrderPlatformCommission()))
            && (this.getOrderTeamCommission() == null ? other.getOrderTeamCommission() == null : this.getOrderTeamCommission().equals(other.getOrderTeamCommission()))
            && (this.getOrderCashierCommission() == null ? other.getOrderCashierCommission() == null : this.getOrderCashierCommission().equals(other.getOrderCashierCommission()))
            && (this.getOrderAllocationTime() == null ? other.getOrderAllocationTime() == null : this.getOrderAllocationTime().equals(other.getOrderAllocationTime()))
            && (this.getOrderConfirmTime() == null ? other.getOrderConfirmTime() == null : this.getOrderConfirmTime().equals(other.getOrderConfirmTime()))
            && (this.getConfirmOperatorId() == null ? other.getConfirmOperatorId() == null : this.getConfirmOperatorId().equals(other.getConfirmOperatorId()))
            && (this.getReceiveAccount() == null ? other.getReceiveAccount() == null : this.getReceiveAccount().equals(other.getReceiveAccount()))
            && (this.getReceiveRealName() == null ? other.getReceiveRealName() == null : this.getReceiveRealName().equals(other.getReceiveRealName()))
            && (this.getReceiveUrl() == null ? other.getReceiveUrl() == null : this.getReceiveUrl().equals(other.getReceiveUrl()))
            && (this.getCallbackNotifyUrl() == null ? other.getCallbackNotifyUrl() == null : this.getCallbackNotifyUrl().equals(other.getCallbackNotifyUrl()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderToken() == null) ? 0 : getOrderToken().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getNonceStr() == null) ? 0 : getNonceStr().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getOrderAmt() == null) ? 0 : getOrderAmt().hashCode());
        result = prime * result + ((getPayAmt() == null) ? 0 : getPayAmt().hashCode());
        result = prime * result + ((getNotifyUrl() == null) ? 0 : getNotifyUrl().hashCode());
        result = prime * result + ((getOrderPlatformCommission() == null) ? 0 : getOrderPlatformCommission().hashCode());
        result = prime * result + ((getOrderTeamCommission() == null) ? 0 : getOrderTeamCommission().hashCode());
        result = prime * result + ((getOrderCashierCommission() == null) ? 0 : getOrderCashierCommission().hashCode());
        result = prime * result + ((getOrderAllocationTime() == null) ? 0 : getOrderAllocationTime().hashCode());
        result = prime * result + ((getOrderConfirmTime() == null) ? 0 : getOrderConfirmTime().hashCode());
        result = prime * result + ((getConfirmOperatorId() == null) ? 0 : getConfirmOperatorId().hashCode());
        result = prime * result + ((getReceiveAccount() == null) ? 0 : getReceiveAccount().hashCode());
        result = prime * result + ((getReceiveRealName() == null) ? 0 : getReceiveRealName().hashCode());
        result = prime * result + ((getReceiveUrl() == null) ? 0 : getReceiveUrl().hashCode());
        result = prime * result + ((getCallbackNotifyUrl() == null) ? 0 : getCallbackNotifyUrl().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}