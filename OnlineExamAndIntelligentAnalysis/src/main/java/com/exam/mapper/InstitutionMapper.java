package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Institution;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.vo.InstitutionInfoVo;

/**
* @author Administrator
* @description 针对表【institution】的数据库操作Mapper
* @createDate 2024-12-13 21:58:56
* @Entity com.exam.entity.Institution
*/
public interface InstitutionMapper extends BaseMapper<Institution> {

    IPage<InstitutionInfoVo> getInstitutionList(Page<InstitutionInfoVo> page,String keyword);
}




