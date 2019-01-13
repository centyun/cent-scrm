package com.centyun.console.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.centyun.console.service.ConsoleSecurityService;

@Service("consoleSecurityService")
public class ConsoleSecurityServiceImpl implements ConsoleSecurityService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        // TODO 对权限进行限制
        return principal instanceof UserDetails ? true : false;
    }

}
