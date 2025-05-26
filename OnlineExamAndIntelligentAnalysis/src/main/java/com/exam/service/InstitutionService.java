package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Institution;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.vo.InstitutionInfoVo;

/**
* @author Administrator
* @description 针对表【institution】的数据库操作Service
* @createDate 2024-12-13 21:58:56
*/
public interface InstitutionService extends IService<Institution> {

    IPage<InstitutionInfoVo> getInstitutionList(Page<InstitutionInfoVo> page,String keyword);

    Boolean createInstitution(String institutionName);
}
