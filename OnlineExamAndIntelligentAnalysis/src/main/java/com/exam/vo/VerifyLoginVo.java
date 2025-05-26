package com.exam.vo;

import com.exam.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Author:CM
 * Date: 2024/12/24 20:01
 */
@Schema(description = "验证码登录实体")
@Data
public class VerifyLoginVo {
    private String tel;
    private String verifyCode;
    private Role role;
}
