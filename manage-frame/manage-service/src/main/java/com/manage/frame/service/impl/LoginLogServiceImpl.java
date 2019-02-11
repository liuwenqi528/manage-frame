package com.manage.frame.service.impl;

import com.manage.frame.dao.LoginLogDao;
import com.manage.frame.entity.LoginLogEntity;
import com.manage.frame.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2019/1/4
 * Time: 11:22
 * 登录日志服务层实现类
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;
    @Override
    public LoginLogEntity get(String id) {
        return null;
    }

    @Override
    public List<LoginLogEntity> findByQuery(LoginLogEntity entity) {
        return null;
    }

    @Override
    public int save(LoginLogEntity entity) {
        return loginLogDao.insert(entity);
    }

    @Override
    public int update(LoginLogEntity entity) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public List<LoginLogEntity> findAll() {
        return null;
    }
}
