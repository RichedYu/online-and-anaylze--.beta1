package com.exam.vo;

import lombok.Data;

import java.util.List;

/**
 * Author:CM
 * Date: 2025/2/5 19:57
 */
@Data
public class ExamInfoVo {
    private Integer totalScore;
    private Integer duration;
    private Long examId;
    private String examName;
    private List<QuestionByTypeVo> questionByTypeVoList;
}
