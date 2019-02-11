package com.manage.frame.service.impl;

import com.manage.frame.dao.UserDao;
import com.manage.frame.entity.UserEntity;
import com.manage.frame.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by
 * User: Liuwq
 * Date: 2019/1/7
 * Time: 14:28
 */
@Slf4j
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity login(UserEntity ew) {
        return userDao.login(ew);
    }

    /**
     * 登陆成功后，设置登陆的IP地址
     *
     * @param username
     * @param host
     */
    @Override
    public void loginSuccess(String id,String username, String host) {

    }

    /**
     * 登陆失败后，设置登陆的IP地址
     *
     * @param username
     * @param host
     */
    @Override
    public void loginFailure(String username, String host) {

    }
}
