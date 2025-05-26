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
 * @TableName institution
 */
@Schema(description = "学院表")
@TableName(value ="institution")
@Data
public class Institution extends BaseEntity {

    /**
     * 学院名
     */
    @Schema(description = "学院名")
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}