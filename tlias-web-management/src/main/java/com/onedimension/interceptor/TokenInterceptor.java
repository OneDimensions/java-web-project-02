package com.onedimension.interceptor;

import com.onedimension.utils.JwtsUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求路径
        // String requestURI = request.getRequestURI();
        // if (requestURI.contains("/login") || requestURI.contains("/register")) {
        //     log.info("登录放行请求: {}", requestURI);
        //     // 放行
        //     return true;
        // }
        // 2. 获取请求头
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            log.info("token为空, 用户未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 3. 解析token
        try {
            JwtsUtils.parseJwt(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        // 放行
        return true;
    }
}
