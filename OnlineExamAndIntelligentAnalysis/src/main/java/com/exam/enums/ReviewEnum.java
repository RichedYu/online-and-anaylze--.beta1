package com.exam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Author:CM
 * Date: 2025/2/7 22:49
 */

public enum ReviewEnum implements BaseEnum{

    // 定义枚举类型  0：待批阅 1：已批阅
    WAIT_REVIEW("0", "待批阅"),
    REVIEWED("1", "已批阅");

    @EnumValue
    @JsonValue
    private final String code;
    private final String name;

    ReviewEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
