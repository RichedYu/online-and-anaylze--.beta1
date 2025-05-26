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
 * @TableName class_paper
 */
@Schema(description = "班级-试卷关联表")
@TableName(value ="class_paper")
@Data
public class ClassPaper extends BaseEntity {

    /**
     * 试卷ID
     */
    @Schema(description = "试卷ID")
    private Long paperId;

    /**
     * 班级ID
     */
    @Schema(description = "班级ID")
    private Long classId;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}