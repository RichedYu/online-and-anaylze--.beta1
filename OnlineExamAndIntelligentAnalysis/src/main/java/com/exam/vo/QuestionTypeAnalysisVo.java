package com.exam.vo;

import lombok.Data;

/**
 * Author:CM
 * Date: 2025/2/13 16:34
 */
@Data
public class QuestionTypeAnalysisVo {
    private String questionType;
    private Integer questionCount;
    private Integer totalScore;
    private Double userScore;
    private Double scoreRate;
}
