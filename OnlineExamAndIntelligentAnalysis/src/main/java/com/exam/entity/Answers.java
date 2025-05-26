package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName answers
 */
@TableName(value ="answers")
@Data
public class Answers implements Serializable {
    /**
     * answerID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long questionId;

    /**
     * 
     */
    private String answer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}