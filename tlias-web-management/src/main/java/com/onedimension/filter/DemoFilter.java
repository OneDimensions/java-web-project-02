package com.onedimension.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

// @WebFilter(urlPatterns = "/*") // 拦截所有请求
// 如果有多个一样的过滤器, 会组成过滤器链, 按类名字母的顺序依次执行
@Slf4j
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器, web服务器启动时执行, 只执行一次");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 每次拦截请求都会执行
        log.info("执行过滤器");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("销毁过滤器, web服务器关闭时执行, 只执行一次");
    }
}
