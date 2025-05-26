package com.exam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LevelEnum implements BaseEnum{
    // 难度级别分为简单、普通、中等、较难、困难
    EASY("1","简单"),
    NORMAL("2","普通"),
    MEDIUM("3","中等"),
    DIFFICULT("4","较难"),
    HARD("5","困难");

    @EnumValue
    @JsonValue
    private final String code;
    private final String name;

    LevelEnum(String code, String name) {
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

    public static LevelEnum getEnumByCode(String code) {
        for (LevelEnum levelEnum : LevelEnum.values()) {
            if (levelEnum.getCode().equals(code)) {
                return levelEnum;
            }
        }
        return null;
    }
}
