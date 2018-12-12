package com.manage.frame.service.impl;

import com.manage.frame.entity.SysPermission;
import com.manage.frame.entity.UserInfo;
import com.manage.frame.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 19:12
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Override
    public List<SysPermission> selectPermByUser(UserInfo userInfo) {
        return null;
    }
}
