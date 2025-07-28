package com.onedimension.controller;

import com.onedimension.pojo.Emp;
import com.onedimension.pojo.EmpQueryParams;
import com.onedimension.pojo.PageResult;
import com.onedimension.pojo.Result;
import com.onedimension.service.EmpService;
import com.onedimension.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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
}
