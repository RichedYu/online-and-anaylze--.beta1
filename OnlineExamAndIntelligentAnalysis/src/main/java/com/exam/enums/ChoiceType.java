package com.exam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChoiceType implements BaseEnum{
    ONE("0","单选"),
    MORE("1","多选");

    @EnumValue
    @JsonValue
    private final String code;
    private final String name;

    ChoiceType(String code, String name) {
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
