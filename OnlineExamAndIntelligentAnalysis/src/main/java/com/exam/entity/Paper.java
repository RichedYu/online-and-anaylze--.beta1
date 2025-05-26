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
 * @TableName paper
 */
@Schema(description = "试卷表")
@TableName(value ="paper")
@Data
public class Paper extends BaseEntity {

    /**
     * 试卷名
     */
    @Schema(description = "试卷名")
    private String name;

    /**
     * 课程ID
     */
    @Schema(description = "课程ID")
    private Long subjectId;

    /**
     * 考试时间
     */
    @Schema(description = "考试时间")
    private Date examTime;

    /**
     * 考试时长
     */
    @Schema(description = "考试时长")
    private Integer examDuration;

    /**
     * 总分值
     */
    @Schema(description = "总分值")
    private Integer totalScore;

    /**
     * 单选题数目
     */
    @Schema(description = "单选题数目")
    private Integer singleChoiceNum;

    /**
     * 多选题数目
     */
    @Schema(description = "多选题数目")
    private Integer moreChoiceNum;

    /**
     * 判断题数目
     */
    @Schema(description = "判断题数目")
    private Integer judgeNum;

    /**
     * 考生须知
     */
    @Schema(description = "考生须知")
    private String tips;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}