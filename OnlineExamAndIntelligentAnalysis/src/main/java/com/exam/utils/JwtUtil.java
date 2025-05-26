package com.exam.utils;


import com.exam.exception.ExamException;
import com.exam.utils.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Author:CM
 * Date: 2024/10/14 20:33
 */

public class JwtUtil {

    private static SecretKey secretKey = Keys.hmacShaKeyFor("VNRBdmMJI2MLv6kTUAOBMzpdsKCoJPJw".getBytes());

    // 生成jwt的静态方法
    public static String createToken(Long userId,String username){


        String jwt = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 3600000*24*20))
                .setSubject("LOGIN_USER")
                .claim("userId", userId)
                .claim("username", username)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }

    // 解析jwt的静态方法
    public static Claims parseToken(String token){

        if(token == null){
            throw new ExamException(ResultCodeEnum.LOGIN_AUTH);
        }

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody();// 返回jwt中的内容
        }catch (ExpiredJwtException e){
            throw new ExamException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch (JwtException e){
            throw new ExamException(ResultCodeEnum.TOKEN_INVALID);
        }

    }

    public static void main(String[] args) {
        String jwt = createToken(1L, "13888888888");
        System.out.println(jwt);
    }

}
