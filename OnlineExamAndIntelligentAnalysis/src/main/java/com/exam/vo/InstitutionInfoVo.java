package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Author:CM
 * Date: 2025/1/20 14:49
 */
@Data
public class InstitutionInfoVo {
    private Long institutionId;
    private String institutionName;
    private Integer majorCount;
    private Integer studentCount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
