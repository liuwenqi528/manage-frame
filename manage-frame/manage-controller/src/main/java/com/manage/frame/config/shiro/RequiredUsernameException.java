package com.manage.frame.config.shiro;

import org.apache.shiro.authc.AccountException;

/**
 * Created by yangwb on 2017/12/27.
 */
public class RequiredUsernameException extends AccountException {

    public RequiredUsernameException() {
    }

    public RequiredUsernameException(String message) {
        super(message);
    }

    public RequiredUsernameException(Throwable cause) {
        super(cause);
    }

    public RequiredUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}
