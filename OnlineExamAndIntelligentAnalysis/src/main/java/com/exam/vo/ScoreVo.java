package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Author:CM
 * Date: 2024/12/26 16:49
 */
@Data
public class ScoreVo {
    private Long scoreId;
    private Double score;
    private Long paperId;
    private String subject;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date answerDate;
}
