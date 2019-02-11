package com.manage.frame.params;

import lombok.Data;

/**
 * Created by
 * User: Liuwq
 * Date: 2019/1/4
 * Time: 10:26
 */
@Data
public class LoginParam {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否记住账号。不存储到数据库
     */
    private Boolean rememberMe;
}
