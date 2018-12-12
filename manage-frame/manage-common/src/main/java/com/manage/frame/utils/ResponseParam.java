package com.manage.frame.utils;

/**
 * 接口返回值对象（Json对象使用）
 * Created by
 * User: Liuwq
 * Date: 2018/12/7
 * Time: 13:51
 */
public class ResponseParam<T> {
    public static final String ERROR = "0";//请求失败
    public static final String SUCCESS = "1";//请求成功
    public static final String NO_LOGIN = "2";//未登陆
    public static final String NO_AUTHORITY = "3";//无权限
    private T data;
    private String code;
    private String message;

    public ResponseParam() {
        this.code = SUCCESS;
        this.message = "操作成功";
    }

    public ResponseParam(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseParam success() {
        return new ResponseParam(SUCCESS, "操作成功");
    }

    public static ResponseParam success(String message) {
        return new ResponseParam(SUCCESS, message);
    }

    public static ResponseParam success(String code, String message) {
        return new ResponseParam(code, message);
    }

    public static ResponseParam fail() {
        return new ResponseParam(ERROR, "操作失败");
    }

    public static ResponseParam fail(String msg) {
        return new ResponseParam(ERROR, msg);
    }

    public static ResponseParam fail(String code, String msg) {
        return new ResponseParam(code, msg);
    }

    public ResponseParam data(T data) {
        this.data = data;
        return this;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
