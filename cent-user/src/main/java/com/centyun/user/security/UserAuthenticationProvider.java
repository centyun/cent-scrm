package com.centyun.user.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.centyun.core.domain.Administrator;
import com.centyun.core.util.encode.EncryptUtils;

@Component("userAuthenticationProvider")
public class UserAuthenticationProvider implements AuthenticationProvider {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDetailsService administratorService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();// 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials();// 这个是表单中输入的密码
        
        // 这里构建来判断用户是否存在和密码是否正确
        Administrator administrator = null;
        try {
            administrator = (Administrator) administratorService.loadUserByUsername(userName);
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            throw new BadCredentialsException("Login.DBError");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BadCredentialsException("Login.Error");
        }
        if (administrator == null || !EncryptUtils.valid(password, administrator.getPassword())) {
            throw new BadCredentialsException("Login.UserPasswdError");
        }
        Collection<? extends GrantedAuthority> authorities = administrator.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(administrator, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
