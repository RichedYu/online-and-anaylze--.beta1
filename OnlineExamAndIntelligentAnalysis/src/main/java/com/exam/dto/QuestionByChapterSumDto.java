package com.exam.dto;

import com.exam.enums.JudgeEnum;
import com.exam.enums.LevelEnum;
import lombok.Data;

/**
 * Author:CM
 * Date: 2025/1/19 14:46
 */
@Data
public class QuestionByChapterSumDto {
    private Long questionId;
    private Long chapterId;
    private LevelEnum level;
    private JudgeEnum result;
}
