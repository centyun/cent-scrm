package com.centyun.user.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.centyun.user.service.SecurityService;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        // 只要登录成功就具有所有的权限, 本系统不做权限的细分
        return principal instanceof UserDetails ? true : false;
    }

}
