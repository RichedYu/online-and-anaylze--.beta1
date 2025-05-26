package com.exam.vo.questionType;

import lombok.Data;

/**
 * Author:CM
 * Date: 2025/3/18 15:37
 */
@Data
public class SingleVo {
    private Long subjectId;
    private Long chapterId;
    private String knowledgePointIdsStr;
    private String question;
    private String analysis;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswers;
    private String level;
}
