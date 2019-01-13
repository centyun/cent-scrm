package com.centyun.core;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动引入的配置类, 使用本项目配置的只需要 @Import(WebCoreConfig.class)
 * @author yinww
 *
 */

@Configuration
@ComponentScan
@EnableFeignClients
public class WebCoreConfig {
    // auto import
}
