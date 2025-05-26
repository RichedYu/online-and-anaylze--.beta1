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
 * @TableName score_detail
 */
@Schema(description = "成绩详情表")
@TableName(value ="score_detail")
@Data
public class ScoreDetail extends BaseEntity {

    /**
     * 成绩表ID
     */
    @Schema(description = "成绩表ID")
    private Long scoreId;

    /**
     * 试题ID
     */
    @Schema(description = "试题ID")
    private Long questionId;

    /**
     * 试卷_试题ID
     */
    @Schema(description = "试卷_试题ID")
    private Long paperQuestionId;

    /**
     * 保存用户试题答案
     */
    @Schema(description = "保存用户试题答案")
    private String answer;

    /**
     * 保存用户试题得分
     */
    @Schema(description = "保存用户试题得分")
    private Double score;

    /**
     * 参考答案
     */
    @Schema(description = "参考答案")
    private String correctAnswer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}