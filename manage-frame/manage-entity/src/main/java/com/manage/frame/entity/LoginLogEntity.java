package com.manage.frame.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by
 * User: Liuwq
 * Date: 2019/1/4
 * Time: 11:02
 */
@Data
public class LoginLogEntity implements Serializable {

    /**
     * 主键编号
     */
    private String id;

    /**
     * 登录人ID
     */
    private String loginUserId;

    /**
     * 登录人用户名
     */
    private String loginUserName;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 客户端物理地址
     */
    private String clientMac;

    /**
     * 客户端地址
     */
    private String clientAddress;

    /**
     * 登录时间
     */
    private Date loginDate;

    /**
     * 登录状态 0 成功  1失败
     */
    private String loginState;

    /**
     * 登录描述
     */
    private String loginDescription;
}
