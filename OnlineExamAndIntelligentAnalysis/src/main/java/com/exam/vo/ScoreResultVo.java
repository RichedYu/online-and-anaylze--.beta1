package com.exam.vo;

import com.exam.enums.JudgeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;

/**
 * Author:CM
 * Date: 2024/12/30 19:18
 */
@Data
public class ScoreResultVo {
    private Double score;
    private Long studentId;
    private Long paperId;
    private Date answerDate;
    private Integer time;
    @JsonProperty("answers")
    private HashMap<String,String> answer;

}
