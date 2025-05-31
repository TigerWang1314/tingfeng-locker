package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Locker implements Serializable {

  private Integer id;

  private String name;

  private String action;

  private String phone;

  private Integer days;

  private LocalDateTime time;
}
