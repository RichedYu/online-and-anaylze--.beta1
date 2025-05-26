package com.exam.dto;

import com.exam.vo.QuestionInfoVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Author:CM
 * Date: 2025/2/11 16:03
 */
@Data
public class ScoreAnalysisDto {
    private String subjectName;
    private String paperName;
    private String studentName;
    private Integer score;
    private Date answerDate;
    private Integer time;
    private List<QuestionInfoVo> questionInfoVoList;
}
