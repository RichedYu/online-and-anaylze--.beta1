package com.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.exam.enums.ChoiceAnswer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName multi_choice
 */
@Schema(description = "选择题表")
@TableName(value ="multi_choice")
@Data
public class MultiChoice extends BaseEntity {

    /**
     * 题目ID
     */
    @Schema(description = "题目ID")
    private Long questionId;

    /**
     * 选项A
     */
    @Schema(description = "选项A")
    private String optiona;

    /**
     * 选项B
     */
    @Schema(description = "选项B")
    private String optionb;

    /**
     * 选项C
     */
    @Schema(description = "选项C")
    private String optionc;

    /**
     * 选项D
     */
    @Schema(description = "选项D")
    private String optiond;

    /**
     * 正确答案
     */
    @Schema(description = "正确答案")
    private ChoiceAnswer correctAnswer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}