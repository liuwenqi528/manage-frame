package com.manage.frame.constant;

/**
 * Created by
 * User: Liuwq
 * Date: 2019/1/4
 * Time: 11:12
 */
public enum LoginEnum {
    SUCCESS("0", "登录成功"),
    FAILURE("1", "登录失败"),
    PWDERROR("2", "密码错误"),
    LOCKEDACCOUNT("3", "用户已被冻结"),
    UNKNOWNACCOUNT("4", "用户不存在");

    private String value;
    private String label;

    LoginEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
