package com.manage.frame.utils;

import com.manage.frame.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/27
 * Time: 11:27
 *
 * 用户信息
 */
public class UserUtils {



    /**
     * 获取授权主要对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象
     */
    public static UserEntity getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            UserEntity userInfo = (UserEntity)subject.getPrincipal();
            if (userInfo != null) {
                return userInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserEntity();
    }

    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {

        }
        return null;
    }
}
