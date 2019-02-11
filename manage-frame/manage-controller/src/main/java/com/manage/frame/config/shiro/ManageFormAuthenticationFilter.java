package com.manage.frame.config.shiro;


import com.manage.frame.constant.LoginEnum;
import com.manage.frame.entity.LoginLogEntity;
import com.manage.frame.entity.UserEntity;
import com.manage.frame.service.LoginLogService;
import com.manage.frame.service.LoginService;
import com.manage.frame.utils.CustomWebUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 处理 AJAX 登录
 * 如果是 ajax 请求则直接处理，之后直接将信息通过response返回给客户端
 */
@Slf4j
public class ManageFormAuthenticationFilter extends FormAuthenticationFilter {

    //session中存放验证码的key值
    public static final String DEFAULT_CAPTCHA_SESSION_KEY = "verifyCode";
    //    默认的页面验证码组件名称
    private static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    //
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;
    private String captchaSessionKeyParam = DEFAULT_CAPTCHA_SESSION_KEY;
    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginLogService loginLogService;


    /**
     * 获取登陆信息，生成Token
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected CaptchaAuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaAuthenticationToken(username, password, rememberMe, host, captcha);
    }


    //第一步：执行此filter的入口，判断请求是否为登陆请求
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }

            //保存请求
            saveRequest(request);

            //Ajax登录
            if (CustomWebUtils.isAjaxRequest((HttpServletRequest) request)) {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.setHeader("loginStatus", "0");
                Map<String, Object> jsonMap = new HashMap<>();
                jsonMap.put("loginMessage", "未登录或登录已过期");
                CustomWebUtils.writeJSON(resp, jsonMap);
                return false;
            }

            //重定向登录
            redirectToLogin(request, response);
//            saveRequestAndRedirectToLogin(request,response);
            return false;
        }
    }

    /**
     * 第二步：登陆接口执行的方法
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
//        获取登陆参数对象，生成token
        CaptchaAuthenticationToken token = createToken(request, response);

        try {
            //校验验证码
            doCaptchaValidate((HttpServletRequest) request, token);

            //登录
            Subject subject = getSubject(request, response);
            subject.login(token);//此处跳转到realm中进行用户密码校验
            log.info("登陆成功");
            //如果登陆成功则执行以下方法
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            log.info("登陆失败：{}", e.getMessage());
            //如果登陆失败则执行以下方法
            return onLoginFailure(token, e, request, response);
        }
    }

    /**
     * 第三步：登录成功
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        //获取登陆用户信息
        UserEntity userInfo = (UserEntity) subject.getPrincipal();
        //修改用户的登陆信息。
        loginService.loginSuccess(userInfo.getId(), userInfo.getUsername(), CustomWebUtils.getIpAddr(req));

        //创建登陆日志对象
        LoginLogEntity logEntity = getLoginLog(req);

        logEntity.setLoginState(LoginEnum.SUCCESS.getValue());
        logEntity.setLoginDescription(LoginEnum.SUCCESS.getLabel());
        logEntity.setLoginUserId(userInfo.getId());
        //保存日志
        loginLogService.save(logEntity);

//        issueSuccessRedirect(request, response);

        return true;
    }

    /**
     * 第三步：登录失败
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        CaptchaAuthenticationToken cToken = (CaptchaAuthenticationToken) token;
        HttpServletRequest req = (HttpServletRequest) request;

        if (log.isDebugEnabled()) {
            log.debug("Authentication exception", e);
        }
        setFailureAttribute(request, e);
        //修改用户的登陆信息。
        loginService.loginFailure(cToken.getUsername(), CustomWebUtils.getIpAddr(req));

        //创建登陆日志对象
        LoginLogEntity logEntity = getLoginLog(req);

        String className = e.getClass().getName();
        log.info("exception  class name:{}",className);
        if ("IncorrectCredentialsException".equals(className)) {//密码错误
            logEntity.setLoginState(LoginEnum.PWDERROR.getValue());
            logEntity.setLoginDescription(LoginEnum.PWDERROR.getLabel());

        } else if ("LockedAccountException".equals(className)) {//被锁
            logEntity.setLoginState(LoginEnum.LOCKEDACCOUNT.getValue());
            logEntity.setLoginDescription(LoginEnum.LOCKEDACCOUNT.getLabel());

        } else if ("LockedAccountException".equals(className)) {//被锁
            logEntity.setLoginState(LoginEnum.LOCKEDACCOUNT.getValue());
            logEntity.setLoginDescription(LoginEnum.LOCKEDACCOUNT.getLabel());

        } else if ("UnknownAccountException".equals(className)) {//不存在
            logEntity.setLoginState(LoginEnum.UNKNOWNACCOUNT.getValue());
            logEntity.setLoginDescription(LoginEnum.UNKNOWNACCOUNT.getLabel());

        } else {//失败
            logEntity.setLoginState(LoginEnum.FAILURE.getValue());
            logEntity.setLoginDescription(LoginEnum.FAILURE.getLabel()+":"+e.getMessage());
        }

        logEntity.setLoginUserName(cToken.getUsername());
        //保存日志
        loginLogService.save(logEntity);

        return false;
    }

    protected void doCaptchaValidate(HttpServletRequest request, CaptchaAuthenticationToken token) {
        String sessionCaptcha = (String) request.getSession().getAttribute(getCaptchaSessionKeyParam());

        if (StringUtils.isNotBlank(sessionCaptcha) && !StringUtils.equals(sessionCaptcha, token.getCaptcha())) {
            throw new IncorrectCaptchaException("验证码错误");
        }
    }


    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    public String getCaptchaSessionKeyParam() {
        return captchaSessionKeyParam;
    }

    public void setCaptchaSessionKeyParam(String captchaSessionKeyParam) {
        this.captchaSessionKeyParam = captchaSessionKeyParam;
    }

    //获取验证码的值
    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    private LoginLogEntity getLoginLog(HttpServletRequest request) {
        LoginLogEntity loginLogEntity = new LoginLogEntity();
        try {
            //客户端IP
            String clientIp = CustomWebUtils.getIpAddr(request);
            //客户端名称
            String clientName = CustomWebUtils.getHostName(clientIp);
//            String clientMac =  CustomWebUtils.getMacAddress(clientIp);
            loginLogEntity.setClientIp(clientIp);
            loginLogEntity.setClientName(clientName);
//            loginLogEntity.setClientMac(clientMac);
            loginLogEntity.setId(UUID.randomUUID().toString());
            loginLogEntity.setLoginDate(new Date());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginLogEntity;
    }
}
