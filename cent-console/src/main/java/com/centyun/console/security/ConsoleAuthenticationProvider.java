package com.centyun.console.security;

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
import org.springframework.stereotype.Component;

import com.centyun.core.client.UserFeignClient;
import com.centyun.core.domain.User;
import com.centyun.core.util.encode.AesCryptUtils;
import com.centyun.core.util.encode.EncryptUtils;

@Component("consoleAuthenticationProvider")
public class ConsoleAuthenticationProvider implements AuthenticationProvider {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 这里构建来判断用户是否存在和密码是否正确
        User user = null;
        try {
            String userName = authentication.getName();// 这个获取表单输入中返回的用户名;
            String loginName = AesCryptUtils.getInstance().encryptAes(userName);
            user = (User) userFeignClient.getUserByToken(loginName);
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            throw new BadCredentialsException("Login.DBError");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BadCredentialsException("Login.Error");
        }

        if (user == null) {
            throw new BadCredentialsException("Login.UserPasswdError");
        }
        
        String password = (String) authentication.getCredentials();// 这个是表单中输入的密码
        boolean isAutoLogin = password != null && password.startsWith("ENCD(") && password.endsWith(")");
        // 如果是自动登录则在CaptchaAuthenticationFilter中验证
        if (!isAutoLogin && !EncryptUtils.valid(password, user.getPassword())) {
            throw new BadCredentialsException("Login.UserPasswdError");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
