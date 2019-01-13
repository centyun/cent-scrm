package com.centyun.mail.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.centyun.core.client.UserFeignClient;
import com.centyun.core.domain.ProductVO;

public class BaseController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserFeignClient userFeignClient;

    protected String getMessage(String code, HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if (locale == null) {
            locale = Locale.CHINA;
        }
        return messageSource.getMessage(code, null, locale);
    }

    protected List<ProductVO> getProducts(String url) {
        List<ProductVO> products = userFeignClient.getAvailableProducts();
        return products;
    }

}
