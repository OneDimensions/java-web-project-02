package com.onedimension.config;

import com.onedimension.interceptor.DemoInterceptor;
import com.onedimension.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Array;
import java.util.List;

@Configuration // 表示这是一个配置类
public class WebConfig implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    public WebConfig(DemoInterceptor demoInterceptor, TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        String[] excludePaths = {"/login", "/register"};
        registry.addInterceptor(tokenInterceptor)
                // 指定拦截的请求 /*只能匹配一级路径 /**可以匹配多级路径
                .addPathPatterns("/**")
                // 指定排除的请求
                .excludePathPatterns(excludePaths);
    }
}
