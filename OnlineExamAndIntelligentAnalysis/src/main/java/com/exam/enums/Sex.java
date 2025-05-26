package com.exam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sex implements BaseEnum {
    FEMALE("0","女"),
    MALE("1","男");

    @EnumValue // 作为该枚举的值传入Mysql
    @JsonValue // 作为该枚举的值转换成json格式
    private final String code;
    private final String name;


    Sex(String code, String name) {
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
