package com.centyun.core.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.centyun.core.config.AntiInjectConfig;

/**
 * xss工具类
 * @author yinww
 *
 */

public class XssUtil {

    public static String cleanXSS(String key, String value, AntiInjectConfig antiInjectConfig) {
        if(value != null) {
            if(antiInjectConfig == null) {
                return HtmlUtils.htmlEscape(value);
            }
            
            String params = antiInjectConfig.getIgnoreParams();
            // 如果配置了某个参数忽略防注入保护, 则该参数不做防注入处理
            if (StringUtils.isEmpty(params)) {
                // 没有应该忽略的属性，进行escape
                return HtmlUtils.htmlEscape(value);
            } else {
                List<String> ignoreParams = Arrays.asList(params.split("\\|"));
                if (!ignoreParams.contains(key)) {
                    // 不属于忽略的属性，则进行escape
                    return HtmlUtils.htmlEscape(value);
                }
            }
        }
        return value;
    }

}
