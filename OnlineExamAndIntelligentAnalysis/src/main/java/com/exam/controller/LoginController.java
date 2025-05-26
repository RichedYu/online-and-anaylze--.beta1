package com.exam.controller;

import com.exam.enums.Role;
import com.exam.exception.ExamException;
import com.exam.service.LoginService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.AccountLoginVo;
import com.exam.vo.VerifyLoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author:CM
 * Date: 2024/12/24 19:31
 */
@Tag(name = "登录管理")
@RestController
@RequestMapping("/exam")
public class LoginController {

    @Resource
    private LoginService loginService;

    @Operation(summary = "账户密码登录")
    @PostMapping("/login/byAccount")
    public Result login(@RequestBody AccountLoginVo account){
        HashMap<String,Object> resp = loginService.accountCheck(account);
        if(resp != null){
            System.out.println("ok");
            return Result.ok(resp);
        }else {
            System.out.println("fail");
            throw new ExamException(ResultCodeEnum.AUTH_FAIL);
        }
    }

    @PostMapping("/login/getCode")
    @Operation(summary = "获取短信验证码")
    public Result getCode(@RequestBody VerifyLoginVo loginVo) {
        if(loginVo.getTel() == null || loginVo.getRole() == null){
            throw new ExamException(ResultCodeEnum.DATA_ERROR);
        }
        loginService.getCode(loginVo.getTel(),loginVo.getRole());
        return Result.ok();
    }

    @PostMapping("/login/byVerify")
    @Operation(summary = "使用验证码登录")
    public Result<HashMap<String,Object>> login(@RequestBody VerifyLoginVo loginVo) {
        HashMap<String, Object> map = loginService.loginByVerify(loginVo);
        return Result.ok(map);
    }



}
