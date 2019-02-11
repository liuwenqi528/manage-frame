package com.manage.frame.dao;

import com.manage.frame.entity.LoginLogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2019/1/4
 * Time: 11:25
 * 登录日志持久层接口
 */
@Repository
public interface LoginLogDao {

    LoginLogEntity get(String id);

    List<LoginLogEntity> findByQuery(LoginLogEntity entity);

    int insert(LoginLogEntity entity);

    int update(LoginLogEntity entity);

    int delete(String id);

    List<LoginLogEntity> findAll();
}
