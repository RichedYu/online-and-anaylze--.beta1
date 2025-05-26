package com.exam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Position implements BaseEnum{
    ASSISTANT("1","助教"),
    LECTURER("2","讲师"),
    ASSOCIATE_PROFESSOR("3","副教授"),
    PROFESSOR("4","教授");

    @EnumValue
    @JsonValue
    private final String code;
    private final String name;

    Position(String code, String name) {
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
