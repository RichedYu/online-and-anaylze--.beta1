package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Institution;
import com.exam.service.InstitutionService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import com.exam.vo.InstitutionInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Author:CM
 * Date: 2025/1/20 9:53
 */
@Tag(name = "学院管理")
@RestController
@RequestMapping("/exam")
public class InstitutionController {

    @Resource
    private InstitutionService institutionService;

    // 得到所有学院信息
    @Operation(summary = "得到所有学院信息")
    @GetMapping("/institutionList")
    public Result<List<Institution>> getInstitutionList() {
        System.out.println("正在调用service层方法获得所有学院信息-");
        List<Institution> list = institutionService.list();
        return Result.ok(list);
    }

        // 获得学院列表(分页)学院编号、学院名称、专业数量、学生数量、创建时间、更新时间
    @Operation(summary = "获得学院列表")
    @GetMapping("/institutionList/{current}/{size}/{keyword}")
    public Result<IPage<InstitutionInfoVo>> getInstitutionList(@PathVariable("current") Integer current,
                                                               @PathVariable("size") Integer size,
                                                               @PathVariable(value = "keyword",required = false) String keyword) {
        System.out.println("正在调用service层方法获得分页数据-----------");
        assert current != null;
        assert size != null;
        // 调用service层方法获得分页数据
        Page<InstitutionInfoVo> page = new Page<>(current, size);
        IPage<InstitutionInfoVo> institutionInfoVos = institutionService.getInstitutionList(page, keyword);
        return Result.ok(institutionInfoVos);
    }

    // 创建学院
    @Operation(summary = "创建学院")
    @PostMapping("/createInstitution")
    public Result<String> createInstitution(@RequestBody String institutionName) {
        System.out.println("正在调用service层方法创建学院-");
        // 去掉学院名称两边的双引号
        institutionName = institutionName.replaceAll("^\"|\"$", "");
        if (institutionName == null || institutionName.trim().equals("")) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        // 查找数据库是否存在该学院名称
        LambdaQueryWrapper<Institution> wr = new LambdaQueryWrapper<>();
        wr.eq(Institution::getName, institutionName);
        Institution institution = institutionService.getOne(wr);
        if (institution != null) {
            return Result.fail(ResultCodeEnum.INSERT_ERROR);
        }
        Boolean flag = institutionService.createInstitution(institutionName);
        if (flag) {
            return Result.ok("创建成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    // 删除学院
    @Operation(summary = "删除学院")
    @DeleteMapping("/deleteInstitution/{institutionId}")
    public Result<String> deleteInstitution(@PathVariable("institutionId") Long institutionId) {
        System.out.println("正在调用service层方法删除学院-");
        if (institutionId == null){
            return Result.fail(ResultCodeEnum.PARAM_ERROR);
        }
        boolean flag = institutionService.removeById(institutionId);
        if (flag) {
            return Result.ok("删除成功");
        } else {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

}
