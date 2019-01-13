package com.centyun.core.xss;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.UrlPathHelper;

import com.centyun.core.config.AntiInjectConfig;

/**
 * url防注入
 * @author yinww
 *
 */

public class XssUrlPathHelper extends UrlPathHelper {

    @Autowired
    private AntiInjectConfig antiInjectConfig;

    @Override
    public Map<String, String> decodePathVariables(HttpServletRequest request, Map<String, String> vars) {
        Map<String, String> result = super.decodePathVariables(request, vars);
        if (!CollectionUtils.isEmpty(result)) {
            for (String key : result.keySet()) {
                result.put(key, cleanXSS(key, result.get(key)));
            }
        }
        return result;
    }

    @Override
    public MultiValueMap<String, String> decodeMatrixVariables(HttpServletRequest request,
            MultiValueMap<String, String> vars) {
        MultiValueMap<String, String> mvm = super.decodeMatrixVariables(request, vars);
        if (!CollectionUtils.isEmpty(mvm)) {
            for (String key : mvm.keySet()) {
                List<String> value = mvm.get(key);
                for (int i = 0; i < value.size(); i++) {
                    value.set(i, cleanXSS(key, value.get(i)));
                }
            }
        }
        return mvm;
    }

    private String cleanXSS(String key, String value) {
        if (value != null && antiInjectConfig != null) {
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