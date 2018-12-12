package com.manage.frame.service.impl;

import com.manage.frame.entity.SysRole;
import com.manage.frame.entity.UserInfo;
import com.manage.frame.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 19:12
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Override
    public List<SysRole> selectRoleByUser(UserInfo userInfo) {
        return null;
    }
}
