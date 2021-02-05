package com.example.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderInRate implements Serializable {
    private Long id;

    private String typeName;

    private Short orderType;

    private BigDecimal defaultCommission;

    private BigDecimal defaultPlatformCommission;

    private BigDecimal defaultTeamCommission;

    private BigDecimal cashierCommission;

    private Short cycleType;

    private Short isOpen;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Short getOrderType() {
        return orderType;
    }

    public void setOrderType(Short orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getDefaultCommission() {
        return defaultCommission;
    }

    public void setDefaultCommission(BigDecimal defaultCommission) {
        this.defaultCommission = defaultCommission;
    }

    public BigDecimal getDefaultPlatformCommission() {
        return defaultPlatformCommission;
    }

    public void setDefaultPlatformCommission(BigDecimal defaultPlatformCommission) {
        this.defaultPlatformCommission = defaultPlatformCommission;
    }

    public BigDecimal getDefaultTeamCommission() {
        return defaultTeamCommission;
    }

    public void setDefaultTeamCommission(BigDecimal defaultTeamCommission) {
        this.defaultTeamCommission = defaultTeamCommission;
    }

    public BigDecimal getCashierCommission() {
        return cashierCommission;
    }

    public void setCashierCommission(BigDecimal cashierCommission) {
        this.cashierCommission = cashierCommission;
    }

    public Short getCycleType() {
        return cycleType;
    }

    public void setCycleType(Short cycleType) {
        this.cycleType = cycleType;
    }

    public Short getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Short isOpen) {
        this.isOpen = isOpen;
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
        OrderInRate other = (OrderInRate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTypeName() == null ? other.getTypeName() == null : this.getTypeName().equals(other.getTypeName()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getDefaultCommission() == null ? other.getDefaultCommission() == null : this.getDefaultCommission().equals(other.getDefaultCommission()))
            && (this.getDefaultPlatformCommission() == null ? other.getDefaultPlatformCommission() == null : this.getDefaultPlatformCommission().equals(other.getDefaultPlatformCommission()))
            && (this.getDefaultTeamCommission() == null ? other.getDefaultTeamCommission() == null : this.getDefaultTeamCommission().equals(other.getDefaultTeamCommission()))
            && (this.getCashierCommission() == null ? other.getCashierCommission() == null : this.getCashierCommission().equals(other.getCashierCommission()))
            && (this.getCycleType() == null ? other.getCycleType() == null : this.getCycleType().equals(other.getCycleType()))
            && (this.getIsOpen() == null ? other.getIsOpen() == null : this.getIsOpen().equals(other.getIsOpen()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTypeName() == null) ? 0 : getTypeName().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getDefaultCommission() == null) ? 0 : getDefaultCommission().hashCode());
        result = prime * result + ((getDefaultPlatformCommission() == null) ? 0 : getDefaultPlatformCommission().hashCode());
        result = prime * result + ((getDefaultTeamCommission() == null) ? 0 : getDefaultTeamCommission().hashCode());
        result = prime * result + ((getCashierCommission() == null) ? 0 : getCashierCommission().hashCode());
        result = prime * result + ((getCycleType() == null) ? 0 : getCycleType().hashCode());
        result = prime * result + ((getIsOpen() == null) ? 0 : getIsOpen().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}