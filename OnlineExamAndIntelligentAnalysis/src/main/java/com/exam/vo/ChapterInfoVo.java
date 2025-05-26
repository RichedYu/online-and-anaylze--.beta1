package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Author:CM
 * Date: 2025/1/22 9:42
 */
@Data
public class ChapterInfoVo {
    private Integer chapterId;
    private String chapterName;
    private String subjectName;
    private Integer questionCount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
