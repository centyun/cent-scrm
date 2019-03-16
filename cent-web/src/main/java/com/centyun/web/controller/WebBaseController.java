package com.centyun.web.controller;

import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.centyun.core.client.UserFeignClient;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.ModuleVO;
import com.centyun.core.domain.ProductVO;

public class WebBaseController {
    
    private Logger log = LoggerFactory.getLogger(WebBaseController.class);

    @Value("${CONSOLE_URL}")
    protected String consoleUrl; // 控制台地址

    @Autowired
    protected UserFeignClient userFeignClient;

    @Autowired
    private MessageSource messageSource;

    protected List<ProductVO> getProductsAndModules(HttpServletRequest request) {
        int port = request.getServerPort();
        String serverName = request.getServerName();
        String server = (port == 80 || port == 443) ? serverName : serverName + ":" + port;
        List<ProductVO> products = userFeignClient.getProductsAndModules();
        
        String uri = request.getRequestURI();
        String[] split = uri.split("/");
        String moduleCode = split.length < 2 ? AppConstant.EMPTY : split[1];
        log.info(moduleCode + "===moduleCode====" + uri);
        if("site-admin".equals(moduleCode)) {
            moduleCode = split[2];
        }
        // setActive
        for (ProductVO product : products) {
            String releaseUrl = product.getReleaseUrl();
            if(releaseUrl != null && releaseUrl.length() > 8) { // https:// 的长度是8
                String host = getHost(releaseUrl);
                product.setActive(host.equals(server));
            } else {
                product.setActive(false);
            }
            List<ModuleVO> modules = product.getModules();
            if(modules != null && modules.size() > 0) {
                for (ModuleVO module : modules) {
                    module.setActive(module.getCode().equals(moduleCode));
                }
            }
        }
        return products;
    }
    
    /**
     * 只获取url中域名(或ip:port)的内容
     * @param releaseUrl
     * @return
     */
    private String getHost(String releaseUrl) {
        try {
            URL url = new URL(releaseUrl);
            return url.getHost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 如果通过构造URL解析失败，则通过截取字符串进行比较
        int begin = 7;
        if(releaseUrl.startsWith("https://")) {
            begin = 8; 
        }
        return releaseUrl.substring(begin).split("/")[0];
    }

    
    protected String getMessage(String code, HttpServletRequest request) {
//        Locale locale = (Locale) request.getSession().getAttribute(AppConstant.LOGIN_USER);
        Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if(locale == null) {
            locale = Locale.CHINA;
        }
        return messageSource.getMessage(code, null, locale);
    }
}
