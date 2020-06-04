package com.midea.common.response;

public enum ErrorInfoEnum {
    EC10000("10101", "密码不能为空"),
    EC10001("10002", "账号不能为空"),;


    public String code;
    public String msg;

    private ErrorInfoEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
