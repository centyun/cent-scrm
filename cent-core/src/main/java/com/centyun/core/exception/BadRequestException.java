package com.centyun.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 无效请求异常类
 * @author yinww
 *
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The request you send to Server was bad.")
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 4024789103778927481L;

    public BadRequestException(String message) {
        super(message);
    }
}
