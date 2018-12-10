package com.manage.frame.service.impl;

import com.manage.frame.dao.UserDao;
import com.manage.frame.entity.UserEntity;
import com.manage.frame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/4
 * Time: 14:26
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity get(String id) {
        return userDao.get(id);
    }

    @Override
    public List<UserEntity> findByQuery(UserEntity entity) {
        return userDao.findByQuery(entity);
    }

    @Override
    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(UserEntity entity) {
        entity.setId(UUID.randomUUID().toString());
        return userDao.insert(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(UserEntity entity) {
        return userDao.update(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(String id) {
        return userDao.delete(id);
    }
}
