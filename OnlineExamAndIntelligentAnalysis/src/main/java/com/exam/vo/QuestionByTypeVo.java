package com.exam.vo;

import com.exam.enums.QuestionType;
import lombok.Data;

import java.util.List;

/**
 * Author:CM
 * Date: 2025/2/5 20:02
 */
@Data
public class QuestionByTypeVo {
    private QuestionType questionType;
    private Integer questionNums;
    private Integer totalScore;
    private List<Answer> answers;
}
