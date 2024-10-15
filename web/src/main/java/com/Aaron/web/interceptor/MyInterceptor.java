package com.Aaron.web.interceptor;

import com.Aaron.entity.po.UserInfo;
import com.Aaron.utils.JwtUtils;
import com.Aaron.utils.LoginHolder;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseToken(token);
        String userId = (String) claims.get("userId");
        LoginHolder.set(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginHolder.remove();
    }


}
