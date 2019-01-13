package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.centyun.core.domain.Module;
import com.centyun.user.domain.Manager;
import com.centyun.user.mapper.ManagerMapper;
import com.centyun.user.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService, UserDetailsService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerMapper.getManager(username);
        return manager;
    }

    @Override
    public List<Module> getModules() {
        List<Module> modules = managerMapper.getModules();
        return modules;
    }

    @Override
    public void updateLanguage(Manager manager) {
        managerMapper.updateLanguage(manager);
    }

}
