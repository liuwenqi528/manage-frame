package com.manage.frame.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/4
 * Time: 11:12
 */
@Setter
@Getter
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -686804261437373398L;

    private String id;

    private String username;

    private String password;

    private String truename;

    private String photo;

    private Integer state;

    private String salt;
    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

}





