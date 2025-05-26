package com.exam.utils.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    PARAM_ERROR(202, "参数不正确"),
    SERVICE_ERROR(203, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    ILLEGAL_REQUEST(205, "非法请求"),
    REPEAT_SUBMIT(206, "重复提交"),
    DELETE_ERROR(207, "删除失败"),
    INSERT_ERROR(208,"数据已存在"),
    SAVE_ERROR(209,"保存失败"),
    UPDATE_ERROR(210,"更新失败"),

    ACCOUNT_EXIST_ERROR(301, "账号已存在"),
    CAPTCHA_CODE_ERROR(302, "验证码错误"),
    CAPTCHA_CODE_EXPIRED(303, "验证码已过期"),
    CAPTCHA_CODE_NOT_FOUND(304, "未输入验证码"),
    LOGIN_PHONE_EMPTY(305, "手机号码为空"),
    LOGIN_CODE_EMPTY(306, "验证码为空"),
    SEND_SMS_TOO_OFTEN(307, "验证法发送过于频繁"),
    PHONE_NOT_BIND(308,"该手机号未绑定当前角色账号"),

    LOGIN_AUTH(318, "未登陆"),
    ACCOUNT_NOT_EXIST_ERROR(309, "账号不存在"),
    ACCOUNT_ERROR(310, "账号或密码错误"),
    ACCOUNT_DISABLED_ERROR(311, "该用户已被禁用"),
    ACCESS_FORBIDDEN(312, "无访问权限"),
    AUTH_FAIL(313,"身份验证失败"),
    STUDENT_NOT_EXIST_ERROR(314, "学生不存在"),
    TEACHER_NOT_EXIST_ERROR(315, "教师不存在"),
    COURSE_NOT_EXIST_ERROR(316, "课程不存在"),

    NOT_FOUND_INFO(404,"未查询到信息"),

    TOKEN_EXPIRED(601, "token过期"),
    TOKEN_INVALID(602, "token非法");


    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
