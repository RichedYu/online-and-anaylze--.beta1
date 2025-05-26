package com.exam.vo;

import lombok.Data;

/**
 * Author:CM
 * Date: 2024/12/27 11:52
 */
@Data
public class MultiChoiceVo {
    private String type;
    private Long questionId;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private Integer score;
    private String analysis;
}
