package com.onedimension.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onedimension.mapper.EmpMapper;
import com.onedimension.pojo.Emp;
import com.onedimension.pojo.EmpQueryParams;
import com.onedimension.pojo.PageResult;
import com.onedimension.pojo.Result;
import com.onedimension.service.EmpService;
import com.onedimension.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /**
     * 传统分页查询方法
     */
    // @Override
    // public PageResult<Emp> page(@RequestParam(defaultValue = "1") Integer page,
    //                             @RequestParam(defaultValue = "10") Integer pageSize) {
    //     int start = (page - 1) * pageSize;
    //     List<Emp> emps = empMapper.page(start, pageSize);
    //     Long total = empMapper.total();
    //     PageResult<Emp> pageResult = new PageResult<Emp>(total, emps);
    //     return pageResult;
    // }

    @Override
    public PageResult<Emp> page(EmpQueryParams empQueryParams) {
        /**
         * pagehelper注意事项
         * 1. sql结尾不能有分号
         * 2. pagehelper仅能对紧跟在其后面的第一个查询语句进行分页处理
         */
        log.info("canshu=====: {}", empQueryParams);
        // 1. 设置分页参数
        PageHelper.startPage(empQueryParams.getPage(), empQueryParams.getPageSize());
        // 2. 执行查询 PageHelper第一个查询语句
        List<Emp> empList = empMapper.page(empQueryParams);

        // 3. 封装结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public Result add(Emp emp) {
        empMapper.add(emp);
        return ResultUtil.success();
    }
}
