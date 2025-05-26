package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Author:CM
 * Date: 2024/12/26 18:43
 */
@Data
public class PaperDetailVo {
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;// 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examTime;// 开始时间
    private String grade;// 年级
    private String institute;// 学院
    private String major;// 专业
    private Long paperId;// 试卷编号
    private String subject;// 课程
    private String tips;// 考生须知
    private Integer totalScore;// 总分
    private Integer totalTime;// 限时

}
