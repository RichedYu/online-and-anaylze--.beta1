package com.exam.exception;

import com.exam.utils.result.ResultCodeEnum;
import lombok.Data;

/**
 * Author:CM
 * Date: 2024/10/13 21:26
 */
@Data
public class ExamException extends RuntimeException{

    private Integer code;

    public ExamException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public ExamException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

}
