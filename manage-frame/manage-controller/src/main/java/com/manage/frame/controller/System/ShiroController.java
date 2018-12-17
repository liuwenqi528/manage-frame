package com.manage.frame.controller.System;

import com.manage.frame.entity.UserEntity;
import com.manage.frame.entity.UserInfo;
import com.manage.frame.utils.ResponseParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
@Slf4j
public class ShiroController {

    /**
     * 登录方法
     *
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParam ajaxLogin(@RequestBody UserEntity userEntity) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUsername(), userEntity.getPassword(),userEntity.getRememberMe());
        try {
            subject.login(token);
//            UserInfo userInfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
            Object userInfo =subject.getPrincipal();
            Object sessionId = subject.getSession().getId();
            log.info("userInfo信息：{}", userInfo);
            log.info("sessionId信息：{}", sessionId);
            return ResponseParam.success("登陆成功").data(userInfo);
        } catch (IncorrectCredentialsException e) {
            log.debug("密码错误");
            e.printStackTrace();
            return ResponseParam.fail(ResponseParam.ERROR, "密码错误");
        } catch (LockedAccountException e) {
            e.printStackTrace();
            return ResponseParam.fail(ResponseParam.ERROR, "登录失败，该用户已被冻结");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return ResponseParam.fail(ResponseParam.ERROR, "该用户不存在");
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
        return ResponseParam.success("登陆成功");

    }
}
