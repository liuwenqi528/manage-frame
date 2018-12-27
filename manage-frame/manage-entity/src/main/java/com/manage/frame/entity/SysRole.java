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
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Boolean available;
    private String description;
    private String role;



}
