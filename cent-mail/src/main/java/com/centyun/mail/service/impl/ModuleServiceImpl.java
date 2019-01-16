package com.centyun.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.core.domain.Module;
import com.centyun.core.service.ModuleService;
import com.centyun.mail.mapper.ModuleMapper;

@Service
public class ModuleServiceImpl implements ModuleService {
    
    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> getModules() {
        Long userId = null;
        Long tenantId = null;
        return moduleMapper.getModules(userId, tenantId);
    }

}
