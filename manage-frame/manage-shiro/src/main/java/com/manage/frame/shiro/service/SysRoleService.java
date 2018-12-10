package com.manage.frame.shiro.service;

import com.manage.frame.shiro.entity.SysRole;
import com.manage.frame.shiro.entity.UserInfo;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 19:11
 */
public interface SysRoleService {

    List<SysRole> selectRoleByUser(UserInfo userInfo);
}
