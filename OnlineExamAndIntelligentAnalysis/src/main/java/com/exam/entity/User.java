package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.exam.enums.Role;
import com.exam.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@Schema(description = "用户表")
@TableName(value ="user")
@Data
public class User extends BaseEntity {

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 性别（0：女，1：男）
     */
    @Schema(description = "性别 0：女 1：男")
    private Sex sex;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String tel;

    /**
     * 电子邮件
     */
    @Schema(description = "电子邮件")
    private String email;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @TableField(value = "pwd",select = false)
    private String pwd;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String cardId;

    /**
     * 角色（0：管理员，1：老师，2：学生）
     */
    @Schema(description = "角色 0：管理员 1：老师 2：学生")
    private Role role;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}