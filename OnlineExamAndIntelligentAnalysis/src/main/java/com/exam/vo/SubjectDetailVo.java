package com.exam.vo;

import lombok.Data;

/**
 * Author:CM
 * Date: 2025/1/17 15:55
 */
@Data
public class SubjectDetailVo {
    private String subjectName;
    private Integer examCount;
    private Double maxScore;
    private Double minScore;
    private Double avgScore;
    private String level;

}
