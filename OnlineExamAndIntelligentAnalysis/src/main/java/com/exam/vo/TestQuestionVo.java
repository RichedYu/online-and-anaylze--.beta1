package com.exam.vo;

import lombok.Data;

/**
 * Author:CM
 * Date: 2024/12/27 14:40
 */
@Data
public class TestQuestionVo {
    private Integer questionNumByType;
    private Integer totalScoreByType;
    private String questionType;
}
