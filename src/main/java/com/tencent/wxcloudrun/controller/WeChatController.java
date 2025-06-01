package com.tencent.wxcloudrun.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import lombok.Data;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wechat")
public class WeChatController {

    @Autowired
    private WxMaService wxMaService;

    final Logger logger;

    public WeChatController() {
        this.logger = LoggerFactory.getLogger(WeChatController.class);
    }

    @PostMapping("/decrypt-phone")
    public ResponseEntity<?> decryptPhone(
            @RequestParam String code,
            @RequestParam String encryptedData,
            @RequestParam String iv) {

        try {
            // 1. 获取session_key
            WxMaJscode2SessionResult session = wxMaService.getUserService()
                    .getSessionInfo(code);

            // 2. 解密手机号
            WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService()
                    .getPhoneNoInfo(session.getSessionKey(), encryptedData, iv);

            return ResponseEntity.ok(phoneNoInfo);

        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getError().getErrorMsg()));
        }
    }

    @Data
    private static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}