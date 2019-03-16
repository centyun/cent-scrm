package com.centyun.core.interceptor;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.centyun.core.config.AntiInjectConfig;

/**
 * 防注入拦截器
 * @author yinww
 *
 */
public class AntiInjectInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(AntiInjectInterceptor.class);
    private final String ANTI_WHITE_LIST = "www.baidu.com|www.goolge.com|www.360.cn|www.so.com|cn.bing.com|www.sogou.com";

    @Autowired
    private AntiInjectConfig antiInjectConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURL().toString();

        // 防盗链, 跨站攻击, 忽略掉白名单，如baidu.com
        String referrer = request.getHeader("referer");
        if (!checkWhiteDomains(referrer, request)) {
            log.error(url + " 被盗链，被盗地址为 " + referrer);
            return false; // 不在白名单中则不允许访问
        }

        // 获取请求所有参数，校验防止SQL注入，防止XSS漏洞
        if (!checkParams(request, url, request.getParameterNames())) {
            return false;
        }
        // 请求头参数防注入
        if (!checkParams(request, url, request.getHeaderNames())) {
            return false;
        }
        return true;
    }

    private boolean checkWhiteDomains(String referrer, HttpServletRequest request) {
        if (StringUtils.isEmpty(referrer)) {
            return true;
        }

        String schema = referrer.startsWith("https://") ? "https://" : "http://";
        String serverName = request.getServerName();
        if(serverName.split("\\.").length > 2) {
            // 如果是相同的主域名则也认为在白名单内, 通过验证
            PathMatcher matcher = new AntPathMatcher();
            String pattern = schema + "*" + serverName.substring(serverName.indexOf("."));
            String pattern1 = pattern + "/**";
            String pattern2 = pattern1 + "/*";
            boolean match = matcher.match(pattern, referrer) ||
                    matcher.match(pattern1, referrer) || matcher.match(pattern2, referrer);
            if(match) {
                return true;
            }
        }

        String basePath = schema + serverName;
        if (referrer.lastIndexOf(basePath) == 0) {
            return true; // referrer包含当前域名不做校验，返回true
        }

        String refererWhiteList = antiInjectConfig.getWhiteDomains();
        if (StringUtils.isEmpty(refererWhiteList)) {
            refererWhiteList = ANTI_WHITE_LIST;
        }

        List<String> refererAntiArray = Arrays.asList(refererWhiteList.split("\\|"));
        for (String refer : refererAntiArray) {
            if (referrer.contains(refer)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkParams(HttpServletRequest request, String url, Enumeration<?> params) {
        List<String> ignoreParams = Arrays.asList(antiInjectConfig.getIgnoreParams().split("\\|"));
        String paramName = null;
        String paramVale = null;
        while (params.hasMoreElements()) {
            paramName = (String) params.nextElement();
            paramVale = request.getParameter(paramName);
            if (StringUtils.isEmpty(paramVale)) {
                continue;
            }
            
            // 忽略base64Image参数，其中值超过1000且不包括空格
            if("base64Image".equals(paramName) && paramVale.length() > 1000 && !paramVale.contains(" ")) {
                continue;
            }

            String value = paramVale.toLowerCase().trim();
            if (hasSensitiveWord(value)) { // 检查是否包含敏感词
                log.error("包含可能脱库的敏感词：参数为" + paramName + " ，值为" + paramVale);
                return false;
            }
            if (ignoreParams.contains(paramName)) { // 忽略该参数, 则继续检查其他参数
                continue;
            }

            // 校验是否存在SQL/xss注入信息
            if (checkInject(paramName, value, url)) {
                log.error("非法内容：参数为" + paramName + " ，值为" + paramVale);
                return false;
            }
        }
        return true;
    }

    /**
     * 检查是否包含可能导致脱裤的敏感词，如果包含则返回true，不包含返回false
     * 
     * @param str
     * @return
     */
    private boolean hasSensitiveWord(String str) {
        if (str == null || str.trim().length() == 0)
            return false;

        // 提前过滤一些关键字
        if (str.contains("--") || str.contains("**") || str.contains("extractvalue") || str.contains("xmltype")
                || str.contains("information_schema") || str.contains("schema_name") || str.contains("benchmark")
                || str.contains("concat") || str.contains("elt(") || str.contains("chr(") || str.contains("char(")
                || str.contains("dual") || str.contains("union") || str.contains("trancate") || str.contains("floor")
                || str.contains("database") || str.contains("versions") || str.contains("sleep")
                || str.contains("schemata") || str.contains("sysdate") || str.contains("rownum")
                || str.contains("casewhen") || str.contains("ifnull") || str.contains("waitfor")
                || str.contains("make_set")) {
            return true;
        }

        // 校验sql语句
        if (syntaxCheck(str, "case", "when") || syntaxCheck(str, "select", "(from|into)")
                || syntaxCheck(str, "delete", "from") || syntaxCheck(str, "drop", "database|table")
                || syntaxCheck(str, "insert", "into") || syntaxCheck(str, "update", "set")
                || syntaxCheck(str, "show", "(databases|tables)")) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 检查是否存在非法字符，防止SQL注入
     * 
     * @param str
     *            被检查的字符串
     * @return ture-字符串中存在非法字符，false-不存在非法字符
     */
    private boolean checkInject(String paramName, String str, String url) {
        if (StringUtils.isEmpty(str)) {
            return false;// 如果传入空串则认为不存在非法字符
        }

        Set<String> words = getWords(str);
        if (words.size() > 0) {
            String sql = antiInjectConfig.getSql();
            if (sql != null && sql.trim().length() > 0) {
                List<String> antiSqlArray = Arrays.asList(sql.split("\\|"));
                for (String word : words) {
                    if (antiSqlArray.contains(word)) {
                        log.error("xss防攻击拦截url:" + url + "，原因：" + paramName + "包含特殊字符" + word + "，传入数据为=" + str);
                        return true;
                    }
                }
            }

            String xss = antiInjectConfig.getXss();
            if (xss != null && xss.trim().length() > 0) {
                String ignores = antiInjectConfig.getIgnoreParams();
                List<String> ignoreArray = Arrays.asList(ignores.split("\\|"));
                if (!ignoreArray.contains(paramName)) { // 如果该属性忽略xss攻击, 则不检查
                    List<String> antiXssArray = Arrays.asList(xss.split("\\|"));
                    for (String word : words) {
                        if (antiXssArray.contains(word)) {
                            log.info("xss防攻击拦截url:" + url + "，原因：" + paramName + "包含特殊字符" + word + "，传入数据为=" + str);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static Set<String> getWords(String str) {
        String matcher = "(?:('.+?')|([a-zA-Z]+))"; // 正则表达式, 提取单词
        Set<String> list = new HashSet<String>();
        String value = str.replaceAll("'", " "); // 单引号有时会导致正则表达式拆出来的单词不对
        Matcher m = Pattern.compile(matcher).matcher(value);
        while (m.find()) {
            String val = m.group(0);
            list.add(val);
        }
        return list;
    }

    private boolean syntaxCheck(String str, String one, String two) {
        String any = "([\\d\\D]*)";
        String seperator = "\\b"; // 边界
        StringBuilder sb = new StringBuilder(any).append(seperator).append(one).append(seperator).append(any)
                .append(seperator).append(two).append(seperator).append(any);
        return Pattern.matches(sb.toString(), str);
    }

}
