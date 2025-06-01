package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class PhoneNumberResult {
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
}