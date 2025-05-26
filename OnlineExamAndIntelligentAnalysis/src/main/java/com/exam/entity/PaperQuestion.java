package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName paper_question
 */
@Schema(description = "试卷-题目关联表")
@TableName(value ="paper_question")
@Data
public class PaperQuestion extends BaseEntity {

    /**
     * 试卷ID
     */
    @Schema(description = "试卷ID")
    private Long paperId;

    /**
     * 题目ID
     */
    @Schema(description = "题目ID")
    private Long questionId;

    /**
     * 序列号
     */
    @Schema(description = "序列号")
    private Integer sequence;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}