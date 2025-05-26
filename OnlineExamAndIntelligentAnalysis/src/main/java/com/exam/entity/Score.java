package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.exam.enums.ReviewEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName score
 */
@Schema(description = "成绩表")
@TableName(value ="score")
@Data
public class Score extends BaseEntity {

    /**
     * 分数
     */
    @Schema(description = "分数")
    private Double score;

    /**
     * 学生ID
     */
    @Schema(description = "学生ID")
    private Long studentId;

    /**
     * 试卷ID
     */
    @Schema(description = "试卷ID")
    private Long paperId;

    /**
     * 答题时间
     */
    @Schema(description = "答题时间")
    private Date answerDate;

    /**
     * 总耗时
     */
    @Schema(description = "总耗时")
    private Integer time;

    /**
     * review 1：考试中 2：待批阅 3：已批阅
     */
    @Schema(description = "review")
    private ReviewEnum review;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}