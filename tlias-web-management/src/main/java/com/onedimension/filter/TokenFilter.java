package com.onedimension.filter;

import com.onedimension.utils.JwtsUtils;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

// @WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 获取请求路径
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/login") || requestURI.contains("/register")) {
            log.info("登录放行请求: {}", requestURI);
            // 放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 2. 获取请求头
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            log.info("token为空, 用户未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 3. 解析token
        try {
            JwtsUtils.parseJwt(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);

        // 放行资源访问完毕之后还会继续执行下面的代码
    }

}
