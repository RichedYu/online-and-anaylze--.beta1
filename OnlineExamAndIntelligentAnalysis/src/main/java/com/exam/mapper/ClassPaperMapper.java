package com.exam.mapper;

import com.exam.entity.ClassPaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【class_paper】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.ClassPaper
*/
public interface ClassPaperMapper extends BaseMapper<ClassPaper> {

    // 根据班级ID、科目ID、数量获取班级_试卷表中最近时间创建的试卷ID列表
    List<Long> getClassPaper(Long classId, Long subjectId, Integer nums);

}




