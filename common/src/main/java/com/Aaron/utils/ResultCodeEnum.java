package com.Aaron.utils;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    TOKEN_ERROR(2001,"token解析失败"),
    TOKEN_EMPTY(2002,"token为空"),
    TOKEN_EXPIRED(2003,"token过期"),
    TOKEN_INVALID(2004,"token无效"),


    EMAIL_SEND_ERROR(3001,"邮件发送失败"),
    EMAIL_EXIST(3003,"邮箱已存在"),
    EMAIL_CODE_ERROR(3005,"邮箱验证码错误"),

    UPLOAD_ERROR(4001,"上传失败");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
