package com.manage.frame.service.impl;

import com.manage.frame.dao.UserDao;
import com.manage.frame.entity.UserEntity;
import com.manage.frame.service.UserService;
import com.manage.frame.utils.Digests;
import com.manage.frame.utils.EncodesUtils;
import com.manage.frame.utils.UserState;
import com.manage.frame.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/4
 * Time: 14:26
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";//加密方式，有 MD5  SHA-1
        String credentials = "123456";//密码
        int hashIterations = 1024;//循环次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("admin");
        String pwd = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, 1024).toString();
        System.out.println("pwd===" + pwd);
        System.out.println(new Md5Hash("123456", "admin").toHex());
//        new Sha1Hash();
    }

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
    public int save(UserEntity entity) {
//        获取当前登录人的ID
        String currentUserId = UserUtils.getPrincipal().getId();
        Date date = new Date();
        // 修改
        if (entity != null && StringUtils.isNoneBlank(entity.getId())) {
            if (StringUtils.isNoneBlank(entity.getPassword())) {
                String salt = EncodesUtils.encodeHex(Digests.generateSalt(8));
                log.info("salt:{}",salt);
                String pwd = new Md5Hash(entity.getPassword(), salt, 1024).toHex();
                entity.setSalt(salt);
                entity.setPassword(pwd);
            }
            entity.setUpdateTime(date);
            entity.setUpdateUser(currentUserId);
            return userDao.update(entity);
        } else {
            //添加
            entity.setId(UUID.randomUUID().toString());
//        ByteSource credentialsSalt = ByteSource.Util.bytes(entity.getUsername());
//        String pwd = new SimpleHash("MD5",entity.getPassword(),credentialsSalt,1024).toString();
            String salt = EncodesUtils.encodeHex(Digests.generateSalt(8));
            log.info("salt:{}",salt);
            String pwd = new Md5Hash(entity.getPassword(), salt, 1024).toHex();
            entity.setSalt(salt);
            entity.setPassword(pwd);
            entity.setCreateTime(date);
            entity.setCreateUser(currentUserId);
            entity.setState(UserState.USE.getCode());
            entity.setDelFlag("0");
            return userDao.insert(entity);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public int update(UserEntity entity) {
        return userDao.update(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(String id) {
        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setDelFlag("1");
        entity.setState(UserState.DEL.getCode());
        return userDao.update(entity);
    }

}
