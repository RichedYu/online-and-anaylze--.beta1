package com.exam.exception;

import com.exam.utils.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:CM
 * Date: 2024/10/13 10:39
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(ExamException.class)
    @ResponseBody
    public Result handle(ExamException e){
        e.printStackTrace();
        return Result.fail(e.getCode(),e.getMessage());
    }

}
