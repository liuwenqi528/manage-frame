package com.manage.frame.service;

import com.manage.frame.entity.SysRole;
import com.manage.frame.entity.UserInfo;

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
