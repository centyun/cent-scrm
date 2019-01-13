package com.centyun.core.xss;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.HtmlUtils;

import com.centyun.core.config.AntiInjectConfig;
import com.centyun.core.util.XssUtil;

/**
 * xss请求包装类 
 * @author yinww
 *
 */

public class XssRequestWrapper extends HttpServletRequestWrapper {

    private AntiInjectConfig antiInjectConfig;

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
        antiInjectConfig = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext())
                .getBean(AntiInjectConfig.class);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return XssUtil.cleanXSS(name, value, antiInjectConfig);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return XssUtil.cleanXSS(name, value, antiInjectConfig);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return values;
        }

        if (antiInjectConfig == null) {
            return cleanXss(values);
        }

        String params = antiInjectConfig.getIgnoreParams();
        // 如果配置了某个参数忽略防注入保护, 则该参数不做防注入处理
        if (StringUtils.isEmpty(params)) {
            // 没有应该忽略的属性，进行escape
            return cleanXss(values);
        } else {
            List<String> ignoreParams = Arrays.asList(params.split("\\|"));
            if (!ignoreParams.contains(name)) {
                // 不属于忽略的属性，则进行escape
                return cleanXss(values);
            }
        }
        return values;
    }

    private String[] cleanXss(String[] values) {
        int length = values.length;
        String[] escapseValues = new String[length];
        for (int i = 0; i < length; i++) {
            escapseValues[i] = values[i] == null ? values[i] : HtmlUtils.htmlEscape(values[i]);
        }
        return escapseValues;
    }

    @Override
    public Object getAttribute(String name) {
        Object value = super.getAttribute(name);
        if (value != null && value instanceof String) {
            return XssUtil.cleanXSS(name, (String) value, antiInjectConfig);
        }
        return value;
    }
}
