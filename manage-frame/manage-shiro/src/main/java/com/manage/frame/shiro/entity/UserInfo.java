package com.manage.frame.shiro.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/10
 * Time: 19:04
 */
@Data
public class UserInfo {
    private String username;
    private String password;
    private Integer state;
    private String credentialsSalt;
    private List<SysRole> roleList;

}
