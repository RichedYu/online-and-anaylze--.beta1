package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Author:CM
 * Date: 2025/1/23 22:24
 */
@Data
public class QuestionInfoVo {
    private Long questionId;
    private String questionContent;
    private Integer questionScore;
    private Integer questionType;
    private String questionTypeName;
    private Integer questionLevel;
    private String questionLevelName;
    private Long chapterId;
    private String chapterName;

    private Integer score;
    private String answer;
    private String correctAnswer;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
