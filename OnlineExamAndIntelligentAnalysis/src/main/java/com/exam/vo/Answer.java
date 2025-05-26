package com.exam.vo;

import lombok.Data;

/**
 * Author:CM
 * Date: 2025/1/5 0:03
 */
@Data
public class Answer {
    private Long questionId;
    private Integer questionScore;
    private String answer;
    private String analysis;
}
