package com.Aaron.utils;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> ok(int code,String msg,T data){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ok(T data){
        return ok(200,"ok",data);
    }

    public static <T> Result<T> error(int code,String msg,T data){
        return ok(code,msg,data);
    }

    public static <T> Result<T> error(T data){
        return error(201,"error",data);
    }

    public static <T> Result<T> error(int code,String msg){
        return error(code,msg,null);
    }
}
