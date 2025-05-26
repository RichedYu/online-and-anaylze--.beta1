package com.exam.mapper;

import com.exam.entity.PaperQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【paper_question】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.PaperQuestion
*/
public interface PaperQuestionMapper extends BaseMapper<PaperQuestion> {

    int queryQuestionNum(Long paperId);
}




