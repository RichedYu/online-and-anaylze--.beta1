package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.Teacher;
import com.exam.entity.User;
import com.exam.enums.Role;
import com.exam.exception.ExamException;
import com.exam.mapper.UserMapper;
import com.exam.service.TeacherService;
import com.exam.mapper.TeacherMapper;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.TeacherInfoVo;
import com.exam.vo.TeacherManageVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;

/**
* @author Administrator
* @description 针对表【teacher】的数据库操作Service实现
* @createDate 2024-12-13 21:58:56
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public IPage<TeacherManageVo> getTeacherList(Page<TeacherManageVo> page, String keyword) {
        return teacherMapper.getTeacherList(page, keyword);
    }

    @Override
    public TeacherInfoVo getTeacherInfo(Long eno) {
        return teacherMapper.getTeacherInfo(eno);
    }

    @Override
    public boolean saveTeacherInfo(TeacherInfoVo teacherInfoVo) {
        User user = new User();
        user.setName(teacherInfoVo.getName());
        user.setSex(teacherInfoVo.getSex());
        user.setTel(teacherInfoVo.getTel());
        user.setEmail(teacherInfoVo.getEmail());
        user.setCardId(teacherInfoVo.getCardId());
        user.setRole(Role.TEACHER);
        user.setPwd(teacherInfoVo.getPassword());
        // 保存用户信息,并返回用户ID
        int insert = userMapper.insert(user);
        if (insert > 0 && user.getId()!= null) {
            // 保存教师信息
            System.out.println("正在保存教师信息--------");
            Teacher teacher = new Teacher();
            // 教师编号为年份+01+教师数量+1
            String year = String.valueOf(new Date().getYear() + 1900);
            Long count = teacherMapper.selectCount(null);
            Long eno = Long.valueOf(year + "0" + (20000 + count + 1));
            teacher.setEno(eno);
            teacher.setUserId(user.getId());
            if (teacherInfoVo.getInstitutionId() != null &&!teacherInfoVo.getInstitutionId().trim().equals("")) {
                teacher.setInstituteId(Long.valueOf(teacherInfoVo.getInstitutionId()));
            }
            teacher.setPosition(teacherInfoVo.getPosition());
            int insert2 = teacherMapper.insert(teacher);
            if (insert2 > 0) {
                return true;
            }else {
                throw new ExamException(ResultCodeEnum.SAVE_ERROR);
            }
        }

        return false;
    }

    @Override
    public boolean removeByEno(Long eno) {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getEno, eno);
        Teacher teacher = teacherMapper.selectOne(wrapper);
        // 删除用户信息
        if (teacher!= null) {
            int delete = userMapper.deleteById(teacher.getUserId());
            if (delete > 0) {
                // 删除教师信息
                int delete1 = teacherMapper.delete(wrapper);
                if (delete1 > 0) {
                    return true;
                }else {
                    throw new ExamException(ResultCodeEnum.DELETE_ERROR);
                }
            }
        }
                return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTeacherInfo(TeacherInfoVo teacherInfoVo) {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getEno, teacherInfoVo.getEno());
        Teacher teacher = teacherMapper.selectOne(wrapper);
        if (teacher != null) {
            // 更新教师信息
            teacher.setInstituteId(Long.valueOf(teacherInfoVo.getInstitutionId()));
            teacher.setPosition(teacherInfoVo.getPosition());
            int update = teacherMapper.updateById(teacher);
            if (update > 0) {
                // 更新用户信息
                User user = new User();
                user.setId(teacher.getUserId());
                user.setName(teacherInfoVo.getName());
                user.setSex(teacherInfoVo.getSex());
                user.setTel(teacherInfoVo.getTel());
                user.setEmail(teacherInfoVo.getEmail());
                user.setCardId(teacherInfoVo.getCardId());
                user.setRole(Role.TEACHER);
                user.setPwd(teacherInfoVo.getPassword());
                int update1 = userMapper.updateById(user);
                if (update1 > 0) {
                    return true;
                }else {
                    throw new ExamException(ResultCodeEnum.UPDATE_ERROR);
                }
            }

        }
        return false;
    }
}




