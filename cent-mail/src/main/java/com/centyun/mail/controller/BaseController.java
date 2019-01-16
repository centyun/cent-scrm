package com.centyun.mail.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.centyun.core.domain.Module;
import com.centyun.core.domain.ModuleVO;
import com.centyun.core.service.ModuleService;
import com.centyun.mail.MailApplication;
import com.centyun.web.controller.WebBaseController;

public class BaseController extends WebBaseController {
    
    @Autowired
    private ModuleService moduleService;
    
    @Override
    protected List<ModuleVO> getModules(HttpServletRequest request, String productCode) {
        if(MailApplication.CODE.equals(productCode)) {
            List<Module> modules = moduleService.getModules();
            if(modules == null || modules.size() == 0) return null;
            
            List<ModuleVO> moduleVOs = new ArrayList<>(modules.size());
            String a = request.getRequestURI() + request.getRequestURL();
            String moduleCode = a.split("/")[1];
            for (Module module : modules) {
                ModuleVO moduleVO = new ModuleVO(module);
                moduleVO.setActive(module.getCode().equals(moduleCode));
                moduleVOs.add(moduleVO);
            }
            return moduleVOs;
        }
        return super.getModules(request, productCode);
    }

}
