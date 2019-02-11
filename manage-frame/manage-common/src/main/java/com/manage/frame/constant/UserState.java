package com.manage.frame.constant;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/27
 * Time: 13:39
 */
public enum UserState {
    USE(0,"正常"),DEL(1,"已删除"),LOCK(2,"锁定");
    private Integer code;
    private String value;

    UserState(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
