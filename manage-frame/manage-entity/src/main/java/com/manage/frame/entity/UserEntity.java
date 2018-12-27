package com.manage.frame.entity;

import com.manage.frame.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.beans.Transient;
import java.io.Serializable;


/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/4
 * Time: 11:12
 */
@Setter
@Getter
public class UserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -686804261437373398L;

    private String id;

    private String username;

    private String password;

    private String truename;

    private String photo;

    private Integer state;

    private Boolean rememberMe;
    private String salt;

    private FileEntity fileEntity;

    /**
     * 密码盐.
     *
     * @return
     */
    @Transient
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

}





