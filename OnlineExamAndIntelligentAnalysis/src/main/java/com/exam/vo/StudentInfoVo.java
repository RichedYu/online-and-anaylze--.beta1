package com.exam.vo;

import com.exam.enums.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Author:CM
 * Date: 2024/12/31 20:24
 */
@Data
public class StudentInfoVo {
    // 不可修改
    private Long userId;
    private Long studentId;
    private String name;
    private Sex sex;
    private Long institutionId;
    private String instituteName;
    private Long majorId;
    private String majorName;
    private Long classId;
    private String className;
    private String grade;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    // 可修改
    private String tel;
    private String email;
    private String cardId;


}
