package com.centyun.core.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.centyun.core.exception.AutoLoginException;
import com.centyun.core.exception.CaptchaException;
import com.centyun.core.util.encode.EncryptUtils;

public class CaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SPRING_SECURITY_CAPTCHA_KEY = "j_captcha";
    public static final String SESSION_CAPTCHA_KEY = "captcha_key";

    public static final String SESSION_KEY = "sessionKey";
    public static final String SESSION_KEY_ENCODE = "sessionKeyEncode";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String autoLoginKey = getRequestValue(request, SESSION_KEY);
        if (autoLoginKey != null && autoLoginKey.length() > 0) { // 如果是自动登录
            System.out.println("auto login in CaptchaAuthenticationFilter");
            try {
                String autoLoginEncodeKey = getSessionValue(request, SESSION_KEY_ENCODE);
                if (EncryptUtils.valid(autoLoginKey, autoLoginEncodeKey)) {
                    return super.attemptAuthentication(request, response);
                } else {
                    throw new AutoLoginException("");
                }
            } catch (Exception e) {
                throw new AutoLoginException("");
            } finally {
                request.getSession().setAttribute(SESSION_KEY_ENCODE, null); // 校验完后清空session值
            }
        } else {
            System.out.println("captcha login in CaptchaAuthenticationFilter");
            String captcha = getSessionValue(request, SESSION_CAPTCHA_KEY);
            String inputCode = getRequestValue(request, SPRING_SECURITY_CAPTCHA_KEY);
            if (!captcha.equalsIgnoreCase(inputCode)) {
                throw new CaptchaException("Captcha.Invalid");
            }
            return super.attemptAuthentication(request, response);
        }
    }

    private String getRequestValue(HttpServletRequest request, String parameter) {
        return request.getParameter(parameter);
    }

    private String getSessionValue(HttpServletRequest request, String attribute) {
        return (String) request.getSession().getAttribute(SESSION_CAPTCHA_KEY);
    }
}
