package com.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.PaperQuestion;
import com.exam.service.PaperQuestionService;
import com.exam.mapper.PaperQuestionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;



/**
* @author Administrator
* @description 针对表【paper_question】的数据库操作Service实现
* @createDate 2024-12-13 21:58:56
*/
@Service
public class PaperQuestionServiceImpl extends ServiceImpl<PaperQuestionMapper, PaperQuestion>
    implements PaperQuestionService{

    @Resource
    private PaperQuestionMapper paperQuestionMapper;

    @Override
    public int queryQuestionNum(Long paperId) {

        return paperQuestionMapper.queryQuestionNum(paperId);
    }
}




