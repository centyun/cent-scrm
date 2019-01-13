package com.centyun.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.centyun.core.WebCoreConfig;

@SpringBootApplication
@EnableDiscoveryClient
@Import(WebCoreConfig.class)
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class UserApplication {

    public static final String APPNAME = "user";

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
