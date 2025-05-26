package com.exam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Author:CM
 * Date: 2025/2/14 21:05
 */

public enum MasteryLevelEnum{
    // 掌握程度分为初识、了解、熟悉、掌握、精通五个等级
    First("初识"),
    Second("了解"),
    Third("熟悉"),
    Fourth("掌握"),
    Fifth("精通");

    @EnumValue
    @JsonValue
    private final String name;

    MasteryLevelEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
