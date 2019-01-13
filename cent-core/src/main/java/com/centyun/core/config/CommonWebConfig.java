package com.centyun.core.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import com.centyun.core.interceptor.AntiInjectInterceptor;
import com.centyun.core.xss.XssFilter;
import com.centyun.core.xss.XssUrlPathHelper;

/**
 * web相关的配置, 如拦截器、过滤器
 * @author yinww
 *
 */

@Configuration
public class CommonWebConfig implements WebMvcConfigurer {
    @Bean
    public AntiInjectInterceptor antiInjectInterceptor() {
        return new AntiInjectInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 防注入拦截器
        registry.addInterceptor(antiInjectInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean<XssFilter> getXssFilter(){
        XssFilter xssFilter = new XssFilter();
        FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<XssFilter>();
        registrationBean.setFilter(xssFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");  // 拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1);
        return registrationBean;
    }
    
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new XssUrlPathHelper();
//        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

}
