package com.exam.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author:CM
 * Date: 2024/12/27 11:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestionDetailVo {
    private List<TestQuestionVo> testQuestionVos;
    private List<MultiChoiceVo> multiChoices;
    private List<JudgeVo> judges;
}
