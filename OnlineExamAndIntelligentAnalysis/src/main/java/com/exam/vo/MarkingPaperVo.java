package com.exam.vo;

import com.exam.enums.ReviewEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Author:CM
 * Date: 2025/2/8 20:49
 */
@Data
public class MarkingPaperVo {
    private Long scoreId;
    private String studentName;
    private Long paperId;
    private String paperName;
    private String subjectName;
    private String score;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date answerDate;
    private Integer answerTime;
    private ReviewEnum reviewStatus;
}
