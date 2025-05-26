package com.exam.service;

import com.exam.enums.Role;
import com.exam.vo.AccountLoginVo;
import com.exam.vo.VerifyLoginVo;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoginService {

    HashMap<String,Object> accountCheck(AccountLoginVo account);

    void getCode(String phone, Role role);


    HashMap<String,Object> loginByVerify(VerifyLoginVo loginVo);
}
