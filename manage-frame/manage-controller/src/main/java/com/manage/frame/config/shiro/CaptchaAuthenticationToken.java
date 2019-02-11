package com.manage.frame.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptchaAuthenticationToken extends UsernamePasswordToken {

    private String captcha;

    public CaptchaAuthenticationToken() {
    }

    public CaptchaAuthenticationToken(String username, String password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
