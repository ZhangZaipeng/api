package com.example.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MerchantRate implements Serializable {
    private Long id;

    private Long merchantId;

    private Long rateId;

    private BigDecimal totalCommission;

    private BigDecimal platformCommission;

    private BigDecimal teamCommossion;

    private BigDecimal cashierCommossion;

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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public BigDecimal getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(BigDecimal totalCommission) {
        this.totalCommission = totalCommission;
    }

    public BigDecimal getPlatformCommission() {
        return platformCommission;
    }

    public void setPlatformCommission(BigDecimal platformCommission) {
        this.platformCommission = platformCommission;
    }

    public BigDecimal getTeamCommossion() {
        return teamCommossion;
    }

    public void setTeamCommossion(BigDecimal teamCommossion) {
        this.teamCommossion = teamCommossion;
    }

    public BigDecimal getCashierCommossion() {
        return cashierCommossion;
    }

    public void setCashierCommossion(BigDecimal cashierCommossion) {
        this.cashierCommossion = cashierCommossion;
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
        MerchantRate other = (MerchantRate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getRateId() == null ? other.getRateId() == null : this.getRateId().equals(other.getRateId()))
            && (this.getTotalCommission() == null ? other.getTotalCommission() == null : this.getTotalCommission().equals(other.getTotalCommission()))
            && (this.getPlatformCommission() == null ? other.getPlatformCommission() == null : this.getPlatformCommission().equals(other.getPlatformCommission()))
            && (this.getTeamCommossion() == null ? other.getTeamCommossion() == null : this.getTeamCommossion().equals(other.getTeamCommossion()))
            && (this.getCashierCommossion() == null ? other.getCashierCommossion() == null : this.getCashierCommossion().equals(other.getCashierCommossion()))
            && (this.getIsOpen() == null ? other.getIsOpen() == null : this.getIsOpen().equals(other.getIsOpen()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getRateId() == null) ? 0 : getRateId().hashCode());
        result = prime * result + ((getTotalCommission() == null) ? 0 : getTotalCommission().hashCode());
        result = prime * result + ((getPlatformCommission() == null) ? 0 : getPlatformCommission().hashCode());
        result = prime * result + ((getTeamCommossion() == null) ? 0 : getTeamCommossion().hashCode());
        result = prime * result + ((getCashierCommossion() == null) ? 0 : getCashierCommossion().hashCode());
        result = prime * result + ((getIsOpen() == null) ? 0 : getIsOpen().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}