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
 * @TableName class
 */
@Schema(description = "班级表")
@TableName(value ="class")
@Data
public class Clazz extends BaseEntity {

    /**
     * 班级名
     */
    @Schema(description = "班级名")
    private String name;

    /**
     * 教师ID
     */
    @Schema(description = "教师ID")
    private Integer teacherId;

    /**
     * 年级
     */
    @Schema(description = "年级")
    private String grade;

    /**
     * 专业ID
     */
    @Schema(description = "专业ID")
    private Long majorId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}