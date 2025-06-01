package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class LockerRequest {

  private String name;
  private String phone;
  private String action;
  private Integer days;

}
