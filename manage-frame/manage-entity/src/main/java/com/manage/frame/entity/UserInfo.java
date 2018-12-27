package com.manage.frame.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uid;
    private String name;
    private String password;
    private String salt;
    private Integer state;
    private String username;

}
