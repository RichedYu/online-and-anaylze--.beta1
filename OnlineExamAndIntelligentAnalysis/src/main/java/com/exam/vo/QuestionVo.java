package com.exam.vo;

import lombok.Data;

/**
 * Author:CM
 * Date: 2025/2/6 19:57
 */
@Data
public class QuestionVo {
    private Long questionId;
    private String question;
    private Integer score;
    private String type;
    private Integer answerCount;
    private String answer;
    private String analysis;
}
