package com.exam.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * Author:CM
 * Date: 2025/2/14 16:44
 */
@Data
public class LeiDaTuVo {
    private List<HashMap<String, Object>> indicator;
    private List<Double> data;
}
