package com.centyun.console.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.centyun.core.exception.AutoLoginException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("consoleAuthenticationFailHander")
public class ConsoleAuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {
    
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private MessageSource messageSource;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        log.info("captch error", exception);
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.BAD_REQUEST.value());
        boolean isAutoLogin = exception instanceof AutoLoginException; // 如果是自动登录失败, 则不显示异常信息
        map.put("msg", isAutoLogin ? "" : getMessage(exception.getMessage(), request));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
    
    private String getMessage(String code, HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if(locale == null) {
            locale = Locale.CHINA;
        }
        return messageSource.getMessage(code, null, locale);
    }
}
