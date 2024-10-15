package com.Aaron.Exection;

import com.Aaron.utils.ResultCodeEnum;
import lombok.Data;

@Data
public class MyException extends RuntimeException {
    private int code;

    public MyException(String msg, int code)
    {
        super(msg);
        this.code = code;
    }

    public MyException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
