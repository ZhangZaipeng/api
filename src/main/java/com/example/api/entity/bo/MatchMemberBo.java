package com.example.api.entity.bo;

import lombok.Data;

@Data
public class MatchMemberBo {
  // 收款员 ID
  private long memberId;
  private String payType;
  // 字段同 tb_user_receivables
  private String realName;
  private String account;
  private String address;
  private int w;
}
