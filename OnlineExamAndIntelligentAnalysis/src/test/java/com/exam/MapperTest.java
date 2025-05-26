package com.exam;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Institution;
import com.exam.mapper.InstitutionMapper;
import com.exam.mapper.ScoreDetailMapper;
import com.exam.vo.InstitutionInfoVo;
import com.exam.vo.TestQuestionVo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author:CM
 * Date: 2024/12/14 14:33
 */
@SpringBootTest
public class MapperTest {

    @Resource
    private InstitutionMapper institutionMapper;
    @Resource
    private ScoreDetailMapper scoreDetailMapper;

    @Test
    public void test1() {
//        Page<InstitutionInfoVo> institutionInfoVoPage = new Page<>(1, 12);
//        IPage<InstitutionInfoVo> result = institutionMapper.getInstitutionList(institutionInfoVoPage, null);
//        System.out.println("结果：" + result.getRecords());
        List<HashMap<String, Object>> result = scoreDetailMapper.getExamQuestionChapter(47L);
        System.out.println("result:" + result);
    }

    @Test
    public void test() {
        List<Long> objects = new ArrayList<>();
        objects.add(28L);
        objects.add(29L);
        List<TestQuestionVo> numByQuestionType = scoreDetailMapper.getNumByQuestionType(objects);
        System.out.println(numByQuestionType);
    }
    @Test
    public void save(){
        Institution institution = new Institution();
        institution.setName("电子与信息工程学院");
        institutionMapper.insert(institution);
    }

    @Test
    public void query(){
        Institution institution = institutionMapper.selectById(1);
        System.out.println(institution);
    }

}
