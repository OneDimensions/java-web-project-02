package com.onedimension.controller;

import com.onedimension.pojo.Emp;
import com.onedimension.pojo.EmpQueryParams;
import com.onedimension.pojo.PageResult;
import com.onedimension.pojo.Result;
import com.onedimension.service.EmpService;
import com.onedimension.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParams empQueryParams) {
        log.info("分页查询: {}", empQueryParams);
        PageResult<Emp> empPageResult = empService.page(empQueryParams);
        return ResultUtil.success(empPageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工: {}", emp);
        empService.saveEmp(emp);
        return ResultUtil.success();
    }

    /**
     *  删除员工
     */
    // @DeleteMapping
    // 参数用数组接收
    // public Result deleteEmp(Integer[] ids) {
    //     log.info("删除员工: {}", ids);
    //     empService.deleteEmp(ids);
    //     return ResultUtil.success();
    // }
    @DeleteMapping
    public Result deleteEmp(@RequestParam("ids") List<Integer> ids) {
        log.info("删除员工: {}", ids);
        empService.deleteEmp(ids);
        return ResultUtil.success();
    }
}
