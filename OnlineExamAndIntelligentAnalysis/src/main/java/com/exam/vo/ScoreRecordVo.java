package com.exam.vo;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Author:CM
 * Date: 2025/1/4 21:53
 */
@Data
public class ScoreRecordVo {
    private Double score;
    private Long studentId;
    private Long paperId;
    private Date answerDate;
    private Integer time;
    private HashMap<Long,String> userAnswers;
    private HashMap<Long,String> analysis;
}
