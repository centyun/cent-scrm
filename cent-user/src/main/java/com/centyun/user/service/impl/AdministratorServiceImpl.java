package com.centyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.centyun.core.domain.Administrator;
import com.centyun.user.domain.AdminMenu;
import com.centyun.user.mapper.AdministratorMapper;
import com.centyun.user.service.AdministratorService;

@Service
public class AdministratorServiceImpl implements AdministratorService, UserDetailsService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator administrator = administratorMapper.getAdministrator(username);
        return administrator;
    }

    @Override
    public List<AdminMenu> getAdminMenus() {
        List<AdminMenu> menus = administratorMapper.getAdminMenus();
        return menus;
    }

    @Override
    public void updateLanguage(Administrator administrator) {
        administratorMapper.updateLanguage(administrator);
    }

}
