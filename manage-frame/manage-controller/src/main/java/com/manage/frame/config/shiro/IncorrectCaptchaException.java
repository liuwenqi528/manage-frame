package com.manage.frame.config.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by yangwb on 2018/1/3.
 */
public class IncorrectCaptchaException extends AuthenticationException {

    public IncorrectCaptchaException() {
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }
}
