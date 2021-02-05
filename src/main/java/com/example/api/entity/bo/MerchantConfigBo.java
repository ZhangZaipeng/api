package com.example.api.entity.bo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MerchantConfigBo {
  private Long merchantId;

  private String appId;

  private String appSecret;

}
