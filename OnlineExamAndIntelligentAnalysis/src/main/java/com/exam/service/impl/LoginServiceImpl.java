package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import com.exam.entity.User;
import com.exam.enums.Role;
import com.exam.exception.ExamException;
import com.exam.mapper.StudentMapper;
import com.exam.mapper.TeacherMapper;
import com.exam.mapper.UserMapper;
import com.exam.service.LoginService;
import com.exam.service.SmsService;
import com.exam.utils.JwtUtil;
import com.exam.utils.RedisConstant;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.utils.sms.CodeUtil;
import com.exam.vo.AccountLoginVo;
import com.exam.vo.VerifyLoginVo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Author:CM
 * Date: 2024/12/24 20:05
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private SmsService smsService;

    @Override
    public HashMap<String, Object> accountCheck(AccountLoginVo account) {
        // 检查对象属性是否为空
        if (account.getAccount() == null) {
            throw new ExamException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (account.getPwd() == null) {
            throw new ExamException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (account.getRole() == null) {
            throw new ExamException(ResultCodeEnum.AUTH_FAIL);
        }

        switch (account.getRole().getCode()) {
            case "0":
                return userExist(account.getAccount(), account.getPwd(), account.getRole());
            case "1":
                // 从教师表查找是否存在该账号
                LambdaQueryWrapper<Teacher> teacherWrapper = new LambdaQueryWrapper<>();
                teacherWrapper.eq(Teacher::getEno, account.getAccount());
                Teacher teacher = teacherMapper.selectOne(teacherWrapper);
                // 存在则从用户表判断密码是否正确
                if (teacher != null) {
                    HashMap<String, Object> map = userExist(teacher.getUserId(), account.getPwd(), account.getRole());
                    map.put("eno", teacher.getEno());
                    return map;
                } else {
                    throw new ExamException(ResultCodeEnum.ACCOUNT_ERROR);
                }
            case "2":
                LambdaQueryWrapper<Student> studentWrapper = new LambdaQueryWrapper<>();
                studentWrapper.eq(Student::getStudentId, account.getAccount());
                Student student = studentMapper.selectOne(studentWrapper);
                if (student != null) {
                    HashMap<String, Object> map = userExist(student.getUserId(), account.getPwd(), account.getRole());
                    map.put("studentId", student.getStudentId());
                    return map;
                } else {
                    throw new ExamException(ResultCodeEnum.ACCOUNT_ERROR);
                }
            default:
                throw new ExamException(ResultCodeEnum.AUTH_FAIL);
        }
    }

    @Override
    public void getCode(String phone,Role role) {
        if(queryByPhone(phone,role) == null){// 判断账号是否存在
            throw new ExamException(ResultCodeEnum.PHONE_NOT_BIND);
        }
        String code = CodeUtil.getRandomCode(6);// 得到一个随机验证码
        // 获取用户前缀
        String key = preByRole(phone,role.getCode());

        // 限制发送短信频率
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if (hasKey) {
            Long ttl = stringRedisTemplate.getExpire(key);
            if (ttl > 0) {
                throw new ExamException(ResultCodeEnum.SEND_SMS_TOO_OFTEN);
            }
        }
        smsService.sendCode(phone,code);// 发送验证码
        // 把信息存入Redis
        stringRedisTemplate.opsForValue().set(key,code,RedisConstant.LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);

    }

    @Override
    public HashMap<String, Object> loginByVerify(VerifyLoginVo loginVo) {
        User user = queryByPhone(loginVo.getTel(), loginVo.getRole());
        if(user == null){
            throw new ExamException(ResultCodeEnum.PHONE_NOT_BIND);
        }
        // 校验loginVo信息是否为空
        if(loginVo.getTel() == null){
            throw new ExamException(ResultCodeEnum.LOGIN_PHONE_EMPTY);
        }
        if(loginVo.getVerifyCode() == null){
            throw new ExamException(ResultCodeEnum.LOGIN_CODE_EMPTY);
        }
        // 获取用户前缀
        String key = preByRole(loginVo.getTel(),loginVo.getRole().getCode());
        // 校验验证码
        String code = stringRedisTemplate.opsForValue().get(key);
        if(code == null){
            throw new ExamException(ResultCodeEnum.CAPTCHA_CODE_EXPIRED);
        }
        if(!code.equals(loginVo.getVerifyCode())){
            throw new ExamException(ResultCodeEnum.CAPTCHA_CODE_ERROR);
        }
        // 返回一个map
        HashMap<String, Object> map = new HashMap<>();
        map.put("userInfo",user);
        map.put("jwt",JwtUtil.createToken(user.getId(),user.getName()));
        if(loginVo.getRole().getCode().equals("1")){
            LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
            teacherLambdaQueryWrapper.eq(Teacher::getUserId,user.getId());
            Teacher teacher = teacherMapper.selectOne(teacherLambdaQueryWrapper);
            map.put("eno",teacher.getEno());
        }else if(loginVo.getRole().getCode().equals("2")){
            LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            studentLambdaQueryWrapper.eq(Student::getUserId,user.getId());
            Student student = studentMapper.selectOne(studentLambdaQueryWrapper);
            map.put("studentId",student.getStudentId());
        }

        return map;
    }


    // 用户检验
    public HashMap<String, Object> userExist(Long userId, String pwd, Role role) {
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getId, userId).eq(User::getPwd, pwd);
        User user = userMapper.selectOne(userWrapper);
        if (user != null) {
            HashMap<String, Object> resp = new HashMap<>();
            resp.put("jwt", JwtUtil.createToken(user.getId(), user.getName()));
            resp.put("userInfo", user);
            return resp;
        } else {
            throw new ExamException(ResultCodeEnum.ACCOUNT_ERROR);
        }
    }

    // 检验手机号是否绑定用户账号
    public User queryByPhone(String phone,Role role){
        // 查询手机号是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getTel,phone).eq(User::getRole,role.getCode());
        return userMapper.selectOne(wrapper);
    }
    // 根据用户角色返回前缀
    public String preByRole(String phone,String code){
        String key = "";
        switch (code){
            case "0": key = RedisConstant.ADMIN_LOGIN_PREFIX+phone;
                break;
            case "1": key = RedisConstant.TEACHER_LOGIN_PREFIX+phone;
                break;
            case "2": key = RedisConstant.STUDENT_LOGIN_PREFIX+phone;
                break;
            default: throw new ExamException(ResultCodeEnum.AUTH_FAIL);
        }
        return key;
    }
}
