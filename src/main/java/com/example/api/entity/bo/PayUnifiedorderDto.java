package com.example.api.entity.bo;

import java.io.Serializable;
import lombok.Data;

@Data
public class PayUnifiedorderDto implements Serializable {
  private String orderId;
  private String accesUrl;
}
