package com.exam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum QuestionType implements BaseEnum{
    //1：单选题 2：多选题 3：判断题，4：填空题，5：简答题，6：应用题
    SINGE_CHOICE("1","单选题"),
    MULTI_CHOICE("2","多选题"),
    JUDGE("3","判断题"),
    FILL_BLANK("4","填空题"),
    SHORT_ANSWER("5","简答题"),
    APPLICATION("6","应用题");


    @EnumValue
    @JsonValue
    private final String code;
    private final String name;

    QuestionType(String code, String name) {
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
