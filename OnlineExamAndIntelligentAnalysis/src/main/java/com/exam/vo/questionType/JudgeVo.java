package com.exam.vo.questionType;

import lombok.Data;

/**
 * Author:CM
 * Date: 2025/3/19 21:14
 */
@Data
public class JudgeVo {
    private Long subjectId;
    private Long chapterId;
    private String knowledgePointIdsStr;
    private String question;
    private String analysis;
    private String correctAnswers;
    private String level;
}
