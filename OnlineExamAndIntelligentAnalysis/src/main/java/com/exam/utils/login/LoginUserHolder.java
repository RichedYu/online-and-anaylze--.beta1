package com.exam.utils.login;

/**
 * Author:CM
 * Date: 2024/10/14 21:59
 */

public class LoginUserHolder {

    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    public static void setLoginUser(LoginUser loginUser){
        threadLocal.set(loginUser);
    }

    public static LoginUser getLoginUser(){
        return threadLocal.get();
    }

    public static void clear(){
        threadLocal.remove();
    }

}
