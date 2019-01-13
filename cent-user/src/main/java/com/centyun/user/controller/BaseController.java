package com.centyun.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.centyun.core.domain.Module;
import com.centyun.core.domain.ModuleVO;
import com.centyun.user.service.ManagerService;

public class BaseController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    protected ManagerService managerService;
    
    protected String getMessage(String code, HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if(locale == null) {
            locale = Locale.CHINA;
        }
        return messageSource.getMessage(code, null, locale);
    }
    
    protected List<ModuleVO> getModules(HttpServletRequest request) {
        String code = request.getRequestURI().split("/")[1];
        List<Module> modules = managerService.getModules();
        List<ModuleVO> moduleVos = new ArrayList<>(modules.size());
        for (Module module : modules) {
            ModuleVO moduleVo = new ModuleVO(module);
            if(code.equals(moduleVo.getCode())) {
                moduleVo.setActive(true);
            }
            moduleVos.add(moduleVo);
        }
        return moduleVos;
    }

}
