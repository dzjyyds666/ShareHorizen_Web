package com.Aaron.utils;


import com.Aaron.Exection.MyException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtils {

    // token过期时间
    private static final long tokenExpiresTime = 60 * 60 * 24 * 30 * 1000L;

    private static final SecretKey tokenSecretKey = Keys.hmacShaKeyFor("com.Aaron.www-com.Aaron.www-com.Aaron.www-".getBytes());

    public static String createToken(String userId){
        return Jwts.builder()
                // 设置主题
                .setSubject("USER_INFO")
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiresTime))
                // 设置用户id
                .claim("userId", userId)
                // 设置签名
                .signWith(tokenSecretKey)
                // 生成token
                .compact();
    }

    public static Claims parseToken(String token){
        if(token == null){
            throw new MyException(ResultCodeEnum.TOKEN_EMPTY);
        }
        try{
            JwtParser parser = Jwts.parserBuilder().setSigningKey(tokenSecretKey).build();
            Jws<Claims> claimsJws = parser.parseClaimsJws(token);
            return claimsJws.getBody();
        }catch (ExpiredJwtException e){
            throw new MyException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch (JwtException e){
            throw new MyException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
}
