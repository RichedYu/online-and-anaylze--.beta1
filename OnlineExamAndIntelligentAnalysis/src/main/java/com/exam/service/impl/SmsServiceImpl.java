package com.exam.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.exam.service.SmsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;



/**
 * Author:CM
 * Date: 2025/1/3 16:03
 */

@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private Client client;

    @Override
    public void sendCode(String phone, String code) {

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName("阿里云短信测试");
        request.setTemplateCode("SMS_154950909");
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        try {
            client.sendSms(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

