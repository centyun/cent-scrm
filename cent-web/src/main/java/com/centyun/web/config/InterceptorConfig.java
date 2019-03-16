package com.centyun.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.centyun.core.interceptor.PassportInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Value("${INTERCEPTOR_INCLUDE_PATHS:/**}")
    private String INTERCEPTOR_INCLUDE_PATHS;

    @Value("${INTERCEPTOR_EXCLUDE_PATHS:}")
    private String INTERCEPTOR_EXCLUDE_PATHS;

    @Bean
    public PassportInterceptor passportInterceptor() {
        return new PassportInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 子系统身份验证拦截器
        InterceptorRegistration interceptor = registry.addInterceptor(passportInterceptor());
        // addPathPatterns 拦截的
        if(INTERCEPTOR_INCLUDE_PATHS != null && INTERCEPTOR_INCLUDE_PATHS.trim().length() > 0) {
            String[] pathPatterns = INTERCEPTOR_INCLUDE_PATHS.trim().split(",");
            if(pathPatterns != null && pathPatterns.length > 0) {
                for (String pathPattern : pathPatterns) {
                    if(pathPattern != null && pathPattern.trim().length() > 0) {
                        interceptor.addPathPatterns(pathPattern.trim());
                    }
                }
            }
        } else {
            interceptor.addPathPatterns("/**");
        }
        
        // excludePathPatterns 不拦截的
        if(INTERCEPTOR_EXCLUDE_PATHS != null && INTERCEPTOR_EXCLUDE_PATHS.trim().length() > 0) {
            String[] excludePaths = INTERCEPTOR_EXCLUDE_PATHS.trim().split(",");
            if(excludePaths != null && excludePaths.length > 0) {
                for (String excludePath : excludePaths) {
                    if(excludePath != null && excludePath.trim().length() > 0) {
                        interceptor.excludePathPatterns(excludePath.trim());
                    }
                }
            }
        }
    }
}