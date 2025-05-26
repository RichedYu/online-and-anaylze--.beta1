package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Author:CM
 * Date: 2025/1/21 21:40
 */
@Data
public class SubjectInfoVo {
    private Long subjectId;
    private String subjectName;
    private String chapterCount;
    private Integer paperCount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

}
