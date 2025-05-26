package com.exam.vo;

import com.exam.enums.MasteryLevelEnum;
import lombok.Data;

/**
 * Author:CM
 * Date: 2025/2/13 22:53
 */
@Data
public class KnowledgePointsAnalysisVo {
    private Long id;
    private String name;
    private Long chapterId;
    private String chapterName;
    private Long questionCount;
    private Long wrongCount;
    private Double scoreRate;
    private Double wrongRate;
    private String masteryLevel;
}
