package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.checkerframework.checker.formatter.qual.Format;

import java.util.Date;
import java.util.List;

/**
 * Author:CM
 * Date: 2025/3/14 21:34
 */
@Data
public class CreateExamVo {
    private String examName;
    private String course;
    private Long subjectId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    private Integer duration;
    private Integer totalScore;
    private Integer singleChoiceCount;
    private Integer multiChoiceCount;
    private Integer trueFalseCount;
    private Integer fillInBlankCount;
    private Integer shortAnswerCount;
    private Integer applicationCount;
    private List<Long> classes;
}
