package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Author:CM
 * Date: 2025/1/21 16:53
 */
@Data
public class MajorInfoVo {
    private Long majorId;
    private String majorName;
    private String institutionName;
    private Integer classCount;
    private Integer studentCount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
