package com.exam.vo;

import com.exam.enums.Position;
import com.exam.enums.Role;
import com.exam.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:CM
 * Date: 2024/12/25 17:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInfoVo {
    private Long eno;
    private String name;
    private String tel;
    private Sex sex;
    private String email;
    private String cardId;
    private Role role;
    private String password;
    private String institutionId;
    private String institution;
    private Position position;
}
