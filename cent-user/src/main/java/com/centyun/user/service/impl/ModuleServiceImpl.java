package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centyun.core.domain.Module;
import com.centyun.user.mapper.ModuleMapper;
import com.centyun.user.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> getModules() {
        return moduleMapper.getModules();
    }

}
