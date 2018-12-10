package com.manage.frame.shiro.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.manage.frame.shiro.entity.UserInfo;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 19:04
 */
public interface UserInfoService {
    UserInfo selectOne(EntityWrapper<UserInfo> ew);
}
