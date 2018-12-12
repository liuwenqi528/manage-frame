package com.manage.frame.dao;

import com.manage.frame.entity.SysRole;
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
public interface SysRoleDao {

    List<SysRole> selectRoleByUser(UserInfo userInfo);
}
