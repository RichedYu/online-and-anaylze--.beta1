package com.exam.vo;

import com.exam.entity.Paper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Author:CM
 * Date: 2024/12/23 10:47
 */
@Schema(description = "试卷信息表")
@Data
public class PaperVo extends Paper {
    @Schema(description = "课程名称")
    private String subjectName;
    @Schema(description = "学院名称")
    private String instituteName;
    @Schema(description = "专业名称")
    private String majorName;
    @Schema(description = "年级")
    private String grade;

}
