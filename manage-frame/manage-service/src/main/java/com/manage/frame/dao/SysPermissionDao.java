package com.manage.frame.dao;


import com.manage.frame.entity.SysPermission;
import com.manage.frame.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
public interface SysPermissionDao {

    List<SysPermission> selectPermByUser(UserInfo userInfo);

}
