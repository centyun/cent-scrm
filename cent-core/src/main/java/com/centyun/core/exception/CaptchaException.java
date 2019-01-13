package com.centyun.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 * @author yinww
 *
 */

public class CaptchaException extends AuthenticationException {

    private static final long serialVersionUID = 3108164738190519397L;

    public CaptchaException(String msg) {
        super(msg);
    }
}
