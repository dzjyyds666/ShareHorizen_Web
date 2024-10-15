package com.Aaron.Exection;

import com.Aaron.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e){
        return Result.error(e.getCode(),e.getMessage());
    }
}
