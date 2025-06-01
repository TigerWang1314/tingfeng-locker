package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class DecryptPhoneRequest {

  private String code;
  private String encryptedData;
  private String iv;
}
