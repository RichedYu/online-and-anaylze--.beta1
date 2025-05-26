package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

/**
 * Author:CM
 * Date: 2025/2/9 15:26
 */
@Data
public class FinalScoreVo {
    private Long scoreId;
    private Double finalScore;
    @JsonProperty("scoreList")
    private HashMap<Long,Object> scoreList;
}
