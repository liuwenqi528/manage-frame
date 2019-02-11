package com.manage.frame.service;

import com.manage.frame.entity.UserEntity;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/4
 * Time: 14:26
 */

public interface UserService {
    UserEntity get(String id);

    List<UserEntity> findByQuery(UserEntity entity);

    int save(UserEntity entity);

    int update(UserEntity entity);

    int delete(String id);

    List<UserEntity> findAll();


}
