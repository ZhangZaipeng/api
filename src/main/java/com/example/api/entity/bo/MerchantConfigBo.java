package com.example.api.entity.bo;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MerchantConfigBo {
  private Long configId;

  private Long merchantId;

  private String appId;

  private String appSecret;

  private Long mrId;

  private BigDecimal totalCommission;

  private BigDecimal platformCommission;

  private BigDecimal teamCommossion;

  private BigDecimal cashierCommossion;

}
