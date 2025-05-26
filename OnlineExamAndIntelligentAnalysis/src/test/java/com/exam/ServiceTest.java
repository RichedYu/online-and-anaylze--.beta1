package com.exam;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Subject;
import com.exam.entity.User;
import com.exam.enums.Sex;
import com.exam.service.KnowledgeMasteryService;
import com.exam.service.SubjectService;
import com.exam.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * Author:CM
 * Date: 2024/12/14 15:03
 */
@SpringBootTest
public class ServiceTest {

    @Resource
    private SubjectService subjectService;
    @Resource
    private UserService userService;
    @Resource
    private KnowledgeMasteryService knowledgeMasteryService;

    @Test
    public void test(){
        System.out.println("测试");
        try {
            String s = knowledgeMasteryService.analyzeMasteryLevel(13, 0.69, 0.23);
            System.out.println("测试结果=" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void save(){
//        Subject subject = new Subject();
//        subject.setName("操作系统");
//        subject.setId(2);
//        boolean update = subjectService.updateById(subject);
//        subjectService.save(subject);

        LambdaUpdateWrapper<Subject> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Subject::getName,"离散数学").set(Subject::getUpdateTime,new Date()).eq(Subject::getId,2);
        boolean update = subjectService.update(wrapper);

        System.out.println(update?"成功":"失败");

    }

    @Test // 使用Wrapper不能触发自动填充功能
    public void update(){
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getSex,Sex.FEMALE).set(User::getUpdateTime,new Date()).eq(User::getId,1);
        boolean update = userService.update(updateWrapper);
        if(update){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }

    @Test
    public void query(){
        List<User> list = userService.list();
        for (User user : list) {
            System.out.println(user);
        }

//        Page<Subject> page = new Page<>(2, 1);
//        Page<Subject> subjectPage = subjectService.page(page);
//        List<Subject> records = subjectPage.getRecords();
//        for (Subject record : records) {
//            System.out.println(record);
//        }
    }
}
