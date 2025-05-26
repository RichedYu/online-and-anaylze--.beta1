package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.exam.enums.JudgeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName judge
 */
@Schema(description = "判断题表")
@TableName(value ="judge")
@Data
public class Judge extends BaseEntity {

    /**
     * 题目ID
     */
    @Schema(description = "题目ID")
    private Long questionId;

    /**
     * 0：错误，1：正确
     */
    @Schema(description = "正确答案 0：错误，1：正确")
    private JudgeEnum correctAnswer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}