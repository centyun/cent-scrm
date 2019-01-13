package com.centyun.core.util;

import javax.servlet.http.HttpServletRequest;

/**
 * http相关的工具类
 * @author yinww
 *
 */

public class HttpUtil {
    
    public static String getRequestUrl(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer(request.getScheme());
        sb.append("://").append(request.getServerName());
        int port = request.getServerPort();
        if (port != 80 && port != 443) {
            sb.append(":").append(port);
        }
        sb.append(request.getContextPath()).append(request.getRequestURI());
        return sb.toString();
    }

}
