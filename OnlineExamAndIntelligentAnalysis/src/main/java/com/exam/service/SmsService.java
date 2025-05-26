package com.exam.service;

/**
 * Author:CM
 * Date: 2025/1/3 16:02
 */

public interface SmsService {
    void sendCode(String phone, String code);
}
