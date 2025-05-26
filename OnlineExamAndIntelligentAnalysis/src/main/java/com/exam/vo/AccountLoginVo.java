package com.exam.vo;

import com.exam.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Author:CM
 * Date: 2024/12/24 20:00
 */
@Schema(description = "账户登录实体")
@Data
public class AccountLoginVo {
    private Long account;
    private String pwd;
    private Role role;
}
