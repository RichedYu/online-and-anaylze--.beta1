package com.exam.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Author:CM
 * Date: 2025/3/16 21:39
 */
@Data
public class KnowledgeInfoVo {
    private Integer knowledgeId;
    private String knowledgeName;
    private String chapterName;
    private Integer questionCount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
