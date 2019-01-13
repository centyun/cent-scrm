package com.centyun.console.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface ConsoleSecurityService {
    
    public boolean hasPermission(HttpServletRequest request, Authentication authentication);
    
}
