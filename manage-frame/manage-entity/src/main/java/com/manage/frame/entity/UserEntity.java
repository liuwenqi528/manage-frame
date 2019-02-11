package com.manage.frame.entity;

import com.manage.frame.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/4
 * Time: 11:12
 */
@Setter
@Getter
@ToString(exclude = {"salt", "rememberMe"})
public class UserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -686804261437373398L;

    /**
     * 主键编号
     */
    private String id;


    /**
     * 用户顺序
     */
    private Integer usersort;

    /**
     * 用户名
     */
    private String username;


    /**
     * 用户描述
     */
    private String description;

    /**
     * 真实姓名
     */
    private String truename;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色名称。多个角色用逗号拼接
     */
    private String roleName;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 登陆错误次数
     */
    private Integer loginErrorCount;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private Date lastLoginDate;

    /**
     * 登录次数
     */
    private Integer loginCount;


    /**
     * 头像文件ID
     */
    private String photo;

    /**
     * 用户状态 0 正常  1删除  2锁定
     */
    private Integer state;



    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 头像文件对象
     */
    private FileEntity fileEntity;

}





