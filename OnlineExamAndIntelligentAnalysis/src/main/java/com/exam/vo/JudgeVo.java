package com.exam.vo;

import lombok.Data;

/**
 * Author:CM
 * Date: 2024/12/27 11:53
 */
@Data
public class JudgeVo {
    private Long questionId;
    private String question;
    private String answer;
    private Integer score;
}
