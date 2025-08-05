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

    /**
     * 根据id查询员工
     */
    @GetMapping("/{id}")
    public Result getEmpById(@PathVariable Integer id) {
        log.info("根据id查询员工: {}", id);
        Emp emp = empService.getEmpById(id);
        return ResultUtil.success(emp);
    }

    /**
     * 修改员工
     */
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp) {
        log.info("修改员工: {}", emp);
        empService.modify(emp);
        return ResultUtil.success();
    }

    /**
     * 查询所有员工
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有员工");
        return ResultUtil.success(empService.list());
    }
}
