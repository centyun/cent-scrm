package com.centyun.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 防注入配置类
 * @author yinww
 *
 */

@Component
@ConfigurationProperties("centyun.core.anti")
public class AntiInjectConfig {

    private String sql;

    private String xss;
    
    private String ignoreParams; // 忽略的参数，不校验
    
    private String whiteDomains; // 域名白名单
    
    public String getSql() {
        return sql == null ? "" : sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getXss() {
        return xss == null ? "" : xss;
    }

    public void setXss(String xss) {
        this.xss = xss;
    }

    public String getIgnoreParams() {
        return ignoreParams == null ? "" : ignoreParams;
    }

    public void setIgnoreParams(String ignoreParams) {
        this.ignoreParams = ignoreParams;
    }

    public String getWhiteDomains() {
        return whiteDomains == null ? "" : whiteDomains;
    }

    public void setWhiteDomains(String whiteDomains) {
        this.whiteDomains = whiteDomains;
    }

}
