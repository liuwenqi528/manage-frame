package com.manage.frame.dao;

import com.manage.frame.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/4
 * Time: 14:30
 */
@Repository
public interface UserDao {
    UserEntity get(String id);

    List<UserEntity> findByQuery(UserEntity entity);

    int insert(UserEntity entity);

    int update(UserEntity entity);

    int delete(String id);

    List<UserEntity> findAll();

    UserEntity login(UserEntity ew);
}
