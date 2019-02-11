package com.manage.frame.controller.System;

import com.manage.frame.entity.UserEntity;
import com.manage.frame.service.LoginLogService;
import com.manage.frame.utils.ResponseParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
@Slf4j
@RequestMapping("/shiro")
public class LoginController {

    @Autowired
    private LoginLogService loginLogService;

//    /**
//     * 登录方法
//     *
//     * @param userEntity
//     * @return
//     */
//    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseParam ajaxLogin(@RequestBody LoginParam userEntity, HttpServletRequest request, HttpServletResponse response) {
//        LoginLogEntity logEntity = new LoginLogEntity();
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRememberMe());
//            subject.login(token);
//            UserEntity userInfo = (UserEntity) subject.getPrincipal();
//            logEntity.setLoginState(LoginEnum.SUCCESS.getValue());
//            logEntity.setLoginDescription(LoginEnum.SUCCESS.getLabel());
//            logEntity.setLoginUserId(userInfo.getId());
//            return ResponseParam.success("登陆成功").data(userInfo);
//        } catch (IncorrectCredentialsException e) {
//            e.printStackTrace();
//            logEntity.setLoginState(LoginEnum.PWDERROR.getValue());
//            logEntity.setLoginDescription(LoginEnum.PWDERROR.getLabel());
//            return ResponseParam.fail(ResponseParam.ERROR, "密码错误");
//        } catch (LockedAccountException e) {
//            e.printStackTrace();
//            logEntity.setLoginState(LoginEnum.LOCKEDACCOUNT.getValue());
//            logEntity.setLoginDescription(LoginEnum.LOCKEDACCOUNT.getLabel());
//            return ResponseParam.fail(ResponseParam.ERROR, "登录失败，该用户已被冻结");
//        } catch (UnknownAccountException e) {
//            e.printStackTrace();
//            logEntity.setLoginState(LoginEnum.UNKNOWNACCOUNT.getValue());
//            logEntity.setLoginDescription(LoginEnum.UNKNOWNACCOUNT.getLabel());
//            return ResponseParam.fail(ResponseParam.ERROR, "该用户不存在");
//        } catch (Exception e) {
//            e.printStackTrace();
//            logEntity.setLoginState(LoginEnum.FAILURE.getValue());
//            logEntity.setLoginDescription(LoginEnum.FAILURE.getLabel());
//            return ResponseParam.fail(ResponseParam.ERROR, "失败");
//        } finally {
//            logEntity.setId(UUID.randomUUID().toString());
//            logEntity.setLoginDate(new Date());
//            loginLogService.save(logEntity);
//        }
//    }


    /**
     * 登出方法
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return ResponseParam.success("登出成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseParam.fail(ResponseParam.ERROR, "失败");
        }
    }

    /**
     * 无权限，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return
     */
    @RequestMapping(value = "/unAuth")
    @ResponseBody
    public ResponseParam unauth() {
        return ResponseParam.fail(ResponseParam.NO_AUTHORITY, "无权限");
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return
     */
    @RequestMapping(value = "/noLogin")
    @ResponseBody
    public ResponseParam noLogin() {
        log.info("noLogin");
        return ResponseParam.fail(ResponseParam.NO_LOGIN, "未登陆");

    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return
     */
    @RequestMapping(value = "/success")
    @ResponseBody
    public ResponseParam success() {
        log.info("success");
        Subject subject = SecurityUtils.getSubject();
        UserEntity userInfo = (UserEntity) subject.getPrincipal();
        return ResponseParam.success("登陆成功").data(userInfo);

    }
}
