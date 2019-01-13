package com.centyun.console.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.centyun.core.client.UserFeignClient;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.Audit;
import com.centyun.core.domain.User;
import com.centyun.core.service.AuditService;
import com.centyun.core.util.CookieUtils;
import com.centyun.core.util.IpUtils;
import com.centyun.core.util.encode.AesCryptUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("consoleAuthenticationSuccessHandler")
public class ConsoleAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private AuditService auditService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Value("${HOME_URL}")
    private String homeUrl;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        Object principal = authentication.getPrincipal();
        if(principal instanceof User) {
            User user = (User) principal;
            Locale locale = (Locale)request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
            if(locale == null) {
                locale = Locale.CHINA;
            }
            if(!locale.toString().equals(user.getLanguage())) {
                user.setLanguage(locale.toString());
                userFeignClient.updateLanguage(user.getId(), locale.toString());
            }
            
            // 记录登录成功的日志
            String ip = request.getParameter("ip");
            Audit audit = new Audit();
            audit.setAction("login");
            audit.setModule("system");
            audit.setContent(ip);
            audit.setIp(ip == null ? null : IpUtils.ipToLong(ip));
            audit.setOperator(user.getId());
            auditService.saveAudit(audit);
            
            // 设置token的cookies
            String token = AesCryptUtils.getInstance().encryptAes(user.getLoginName());
            CookieUtils.setCookie(request, response, AppConstant.TOKEN, token);
        }
        
        // 这里可以根据实际情况，来确定是跳转到页面或者json格式。
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.OK.value());
        map.put("msg", getMessage("Login.Success", request));
        map.put("url", homeUrl);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
        
        //如果是要跳转到某个页面的，比如我们的那个whoim的则
        // getRedirectStrategy().sendRedirect(request, response, "/whoim");
    }
    
    private String getMessage(String code, HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if(locale == null) {
            locale = Locale.CHINA;
        }
        return messageSource.getMessage(code, null, locale);
    }
}
