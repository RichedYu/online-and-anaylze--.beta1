package com.exam.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonIgnore// 返回响应数据时，不带该字段数据
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    @JsonIgnore// 返回响应数据时，不带该字段数据
    private Date updateTime;

    @Schema(description = "逻辑删除")
    @TableLogic// 自动为查询操作增加`is_deleted=0`过滤条件，并将删除操作转为更新语句。默认1：已删除 0：未删除
    @TableField("is_deleted")
    @JsonIgnore// 返回响应数据时，不带该字段数据
    private Byte isDeleted;

}