package com.exam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role implements BaseEnum{
    ADMIN("0","管理员"),
    TEACHER("1","教师"),
    STUDENT("2","学生");

    @EnumValue
    @JsonValue
    private final String code;
    private final String name;

    Role(String code, String name) {
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
