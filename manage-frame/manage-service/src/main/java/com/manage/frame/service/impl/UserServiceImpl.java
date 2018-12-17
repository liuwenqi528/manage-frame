package com.manage.frame.service.impl;

import com.manage.frame.dao.UserDao;
import com.manage.frame.entity.UserEntity;
import com.manage.frame.entity.UserInfo;
import com.manage.frame.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

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
    public UserEntity login(UserEntity ew) {
        return userDao.login(ew);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(UserEntity entity) {
        entity.setId(UUID.randomUUID().toString());
//        ByteSource credentialsSalt = ByteSource.Util.bytes(entity.getUsername());
//        String pwd = new SimpleHash("MD5",entity.getPassword(),credentialsSalt,1024).toString();
        String pwd = new Md5Hash(entity.getPassword(),entity.getUsername(),1024).toHex();
        entity.setPassword(pwd);
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

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";//加密方式，有 MD5  SHA-1
        String credentials = "123456";//密码
        int hashIterations = 1024;//循环次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("admin");
        String pwd = new SimpleHash(hashAlgorithmName,credentials,credentialsSalt,1024).toString();
        System.out.println("pwd==="+pwd);
        System.out.println(new Md5Hash("123456","admin").toHex());
//        new Sha1Hash();
    }
}
