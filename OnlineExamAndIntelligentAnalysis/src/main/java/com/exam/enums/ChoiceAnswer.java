package com.exam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChoiceAnswer {
    A("A","单选A"),
    B("B","单选B"),
    C("C","单选C"),
    D("D","单选D"),
    AB("AB","多选AB"),
    AC("AC","多选AC"),
    AD("AD","多选AD"),
    BC("BC","多选BC"),
    BD("BD","多选BD"),
    CD("CD","多选CD"),
    ABC("ABC","多选ABC"),
    ABD("ABD","多选ABD"),
    ACD("ACD","多选ACD"),
    BCD("BCD","多选BCD"),
    ABCD("ABCD","多选ABCD");


    @EnumValue
    @JsonValue
    private final String code;
    private final String name;

    ChoiceAnswer(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ChoiceAnswer getEnumByCode(String code) {
        for (ChoiceAnswer choiceAnswer : ChoiceAnswer.values()) {
            if (choiceAnswer.getCode().equals(code)) {
                return choiceAnswer;
            }
        }
        return null;
    }
}
