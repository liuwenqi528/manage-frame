package com.manage.frame.entity;

import lombok.Data;
import lombok.ToString;

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
@ToString
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Boolean available;
    private String name;
    private Long parentId;
    private String parentIds;
    private String permission;
    private String resourceType;
    private String url;


}
