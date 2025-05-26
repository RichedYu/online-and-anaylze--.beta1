package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Author:CM
 * Date: 2025/1/22 10:37
 */
@Data
public class ClassInfoVo {
    private Long classId;
    private String className;
    private String grade;
    private String majorName;
    private Integer studentCount;
    private Long teacherId;
    private String teacherName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
