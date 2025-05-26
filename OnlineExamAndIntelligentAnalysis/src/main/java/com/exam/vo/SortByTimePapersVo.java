package com.exam.vo;

import lombok.Data;

/**
 * Author:CM
 * Date: 2024/12/28 11:41
 */
@Data
public class SortByTimePapersVo {
    private Integer all;
    private Integer notStarted;
    private Integer inProgress;
    private Integer ended;
}
