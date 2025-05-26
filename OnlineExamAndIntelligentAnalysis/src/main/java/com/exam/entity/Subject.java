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
 * @TableName subject
 */
@Schema(description = "课程表")
@TableName(value ="subject")
@Data
public class Subject extends BaseEntity {

    /**
     * 课程名
     */
    @Schema(description = "课程名")
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}