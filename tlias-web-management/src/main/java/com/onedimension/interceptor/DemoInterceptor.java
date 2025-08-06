package com.onedimension.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器
 */
@Slf4j
@Component // 交给Spring管理
// 拦截器创建完毕之后还需要在配置类中进行配置生效
public class DemoInterceptor implements HandlerInterceptor {

    // 在资源访问之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("资源访问前置执行拦截器");
        return true; // true: 放行
    }

    // 在资源访问完毕之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("资源访问完毕拦截器");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // 在页面渲染完毕之后执行, 最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("页面渲染完毕拦截器");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
