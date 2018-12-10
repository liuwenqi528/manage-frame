package com.manage.frame.shiro.service;

import com.manage.frame.shiro.entity.SysPermission;
import com.manage.frame.shiro.entity.UserInfo;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 19:12
 */
public interface SysPermissionService {
    List<SysPermission> selectPermByUser(UserInfo userInfo);
}
