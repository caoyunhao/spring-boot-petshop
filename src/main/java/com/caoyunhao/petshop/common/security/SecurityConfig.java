package com.caoyunhao.petshop.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    DigestInterceptor digestInterceptor;
    @Autowired
    SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加摘要验证拦截器
        String[] digestExcludePatterns = {"/", "/login/**", "/verifycode"};
        registry.addInterceptor(digestInterceptor).excludePathPatterns(digestExcludePatterns);//拦截指定url外的全部地址

        // 添加检查权限的拦截器
        String[] excludePatterns = {"/", "/login/**", "/verifycode"};
        registry.addInterceptor(securityInterceptor).excludePathPatterns(excludePatterns);//拦截指定url外的全部地址

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
