package com.centyun.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.centyun.core.domain.ModuleVO;
import com.centyun.user.domain.AdminMenu;
import com.centyun.user.service.AdministratorService;

public class BaseController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    protected AdministratorService administratorService;
    
    protected String getMessage(String code, HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if(locale == null) {
            locale = Locale.CHINA;
        }
        return messageSource.getMessage(code, null, locale);
    }
    
    protected List<ModuleVO> getModules(HttpServletRequest request) {
        String code = request.getRequestURI().split("/")[1];
        List<AdminMenu> adminMenus = administratorService.getAdminMenus();
        List<ModuleVO> moduleVos = new ArrayList<>(adminMenus.size());
        for (AdminMenu module : adminMenus) {
            ModuleVO moduleVo = toModuleVo(module);
            if(code.equals(moduleVo.getCode())) {
                moduleVo.setActive(true);
            }
            moduleVos.add(moduleVo);
        }
        return moduleVos;
    }
    
    private ModuleVO toModuleVo(AdminMenu adminMenu) {
        ModuleVO moduleVo = new ModuleVO();
        moduleVo.setId(adminMenu.getId());
        moduleVo.setName(adminMenu.getName());
        moduleVo.setEnglishName(adminMenu.getEnglishName());
        moduleVo.setIcon(adminMenu.getIcon());
        moduleVo.setCode(adminMenu.getCode());
        moduleVo.setUrl(adminMenu.getUrl());
        moduleVo.setOrderNo(adminMenu.getOrderNo());
        moduleVo.setStatus(adminMenu.getStatus());
        moduleVo.setCreateTime(adminMenu.getCreateTime());
        return moduleVo;
    }

}
