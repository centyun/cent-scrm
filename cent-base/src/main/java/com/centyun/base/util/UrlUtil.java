package com.centyun.base.util;

import org.springframework.util.StringUtils;

public class UrlUtil {

    /**
     * 路径追加参数
     * 
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String addParameter(String url, String parameter, String value) {
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(parameter)) {
            return url;
        }
        
        String a = "";
        if (!url.contains("/")) {// 编码后的URL不可能存在/
            // 编码化url处理 %3F->? %3D->=
            if (url.contains("%3F") && !url.contains("%3D")) {
                a = "";
            } else if (url.contains("%3F") && url.contains("%3D")) {
                a = "%26";
            } else {
                a = "%3F";
            }
        } else {
            // 非编码化url处理
            if (url.contains("?") && !url.contains("=")) {
                a = "";
            } else if (url.contains("?") && url.contains("=")) {
                a = "&";
            } else {
                a = "?";
            }
        }
        return url += (a + parameter + "=" + value);
    }

}
