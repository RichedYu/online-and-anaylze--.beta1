package com.exam.controller;

import com.exam.entity.User;
import com.exam.service.UserService;
import com.exam.utils.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Author:CM
 * Date: 2024/12/16 11:07
 */
@Tag(name = "用户信息管理")
@RestController
@RequestMapping("/exam")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "获取所有用户")
    @GetMapping("/list")
    public Result<List<User>> list(){
        List<User> list = userService.list();
        return Result.ok(list);

    }

    @Operation(summary = "根据用户ID修改用户信息")
    @PutMapping("/updateInfo")
    public Result update(@RequestBody User user){

        boolean b = userService.updateById(user);
        if(b){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

}
