package com.exam.config.interceptor;


import com.exam.utils.JwtUtil;
import com.exam.utils.login.LoginUser;
import com.exam.utils.login.LoginUserHolder;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



/**
 * Author:CM
 * Date: 2024/10/15 1:16
 */

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        Claims claims = JwtUtil.parseToken(token);
        Long userId = claims.get("userId", Long.class);
        String username = claims.get("username", String.class);
        System.out.println("身份验证成功-----token:"+token+"-userId:"+userId+"-username"+username);
        LoginUserHolder.setLoginUser(new LoginUser(userId, username));
        System.out.println("url:"+request.getRequestURI());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.clear();
    }
}
