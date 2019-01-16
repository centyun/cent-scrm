package com.centyun.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.centyun.cms.CmsApplication;
import com.centyun.core.domain.Module;
import com.centyun.core.domain.ModuleVO;
import com.centyun.core.service.ModuleService;
import com.centyun.web.controller.WebBaseController;

public class BaseController extends WebBaseController {
    
    @Autowired
    private ModuleService moduleService;
    
    @Override
    protected List<ModuleVO> getModules(HttpServletRequest request, String productCode) {
        if(CmsApplication.CODE.equals(productCode)) {
            List<Module> modules = moduleService.getModules();
            if(modules == null || modules.size() == 0) return null;
            
            List<ModuleVO> moduleVOs = new ArrayList<>(modules.size());
            String[] uris = request.getRequestURI().split("/");
            String moduleCode = uris.length > 2 ? uris[2] : null;
            for (Module module : modules) {
                ModuleVO moduleVO = new ModuleVO(module);
                moduleVO.setActive(moduleCode != null && module.getCode().equals(moduleCode));
                moduleVOs.add(moduleVO);
            }
            return moduleVOs;
        }
        return super.getModules(request, productCode);
    }
    
}
