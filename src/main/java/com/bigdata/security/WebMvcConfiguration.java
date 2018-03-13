package com.bigdata.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Desciption 所有请求前加上路径前缀
 * Create By  li.bo
 * CreateTime 2018/3/13 10:29
 * UpdateTime 2018/3/13 10:29
 */
@Configuration
@Order()
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new RolePermissionInterceptor()).addPathPatterns("/rest/**");
    }
}
