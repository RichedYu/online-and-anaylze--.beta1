package com.exam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Author:CM
 * Date: 2024/12/14 9:53
 */

public enum JudgeEnum implements BaseEnum{
    WRONG("0","错误"),
    CORRECT("1","正确");

    @EnumValue
    @JsonValue
    private final String code;
    private final String name;

    JudgeEnum(String code, String name) {
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

    public static JudgeEnum getEnumByCode(String code) {
        for (JudgeEnum e : JudgeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
