package com.centyun.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自动登录异常类
 * @author yinww
 *
 */

public class AutoLoginException extends AuthenticationException {

    private static final long serialVersionUID = -6183192306234108570L;

    public AutoLoginException(String msg) {
        super(msg);
    }
}
