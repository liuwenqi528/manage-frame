package com.manage.frame.service;

import com.manage.frame.entity.LoginLogEntity;
import com.manage.frame.entity.UserEntity;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2019/1/4
 * Time: 11:22
 *
 * 登录日志
 */
public interface LoginLogService {

    LoginLogEntity get(String id);

    List<LoginLogEntity> findByQuery(LoginLogEntity entity);

    int save(LoginLogEntity entity);

    int update(LoginLogEntity entity);

    int delete(String id);

    List<LoginLogEntity> findAll();

}
