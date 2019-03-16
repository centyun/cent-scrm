package com.centyun.oa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityRequestMatcherProviderAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.centyun.core.WebCoreConfig;
import com.centyun.web.WebBaseConfig;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@Import({WebCoreConfig.class, WebBaseConfig.class})
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class, SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class
        , SecurityFilterAutoConfiguration.class, SecurityRequestMatcherProviderAutoConfiguration.class})
public class OaApplication {
    
    public static final String CODE = "oa";

    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);
    }

}
