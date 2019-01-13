package com.centyun.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface SecurityService {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication);
    
}
