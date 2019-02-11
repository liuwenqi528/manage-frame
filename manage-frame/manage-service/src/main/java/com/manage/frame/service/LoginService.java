package com.manage.frame.service;

import com.manage.frame.entity.UserEntity;

/**
 * Created by
 * User: Liuwq
 * Date: 2019/1/7
 * Time: 14:27
 */
public interface LoginService {
    UserEntity login(UserEntity ew);

    /**
     * 登陆成功后，设置登陆的IP地址
     * @param id
     * @param host
     */
    void loginSuccess(String id,String username,String host);

    /**
     * 登陆失败后，设置登陆的IP地址
     * @param username
     * @param host
     */
    void loginFailure(String username,String host);
}
