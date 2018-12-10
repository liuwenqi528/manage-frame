package com.manage.frame.shiro.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 19:05
 */
@Data
public class SysRole {

    private String role;
    private List<SysPermission> permissions;


}
