package com.onedimension.controller;

import com.onedimension.pojo.Dept;
import com.onedimension.pojo.Result;
import com.onedimension.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 日志注解, 可以使用log.xxx()打印日志
// RequestMapping在类上注解表示当前类的所有公共路径都是这个
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 可以使用lombok的slf4j注解替代
    // private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    /**
     * 获取部门列表
     */
    @GetMapping
    public Result<List<Dept>> depts() {
        Result<List<Dept>> depts = deptService.getDepts();
        log.info("获取部门列表: {}", depts);
        return deptService.getDepts();
    }

    /**
     * 删除部门
     *
     * @param id 部门id
     */
    // 前端传递参数与服务端形参名一致, 可以不用写@RequestParam映射
    @DeleteMapping
    public Result deleteDept(Integer id) {
        log.info("删除部门: {}", id);
        return deptService.deleteDept(id);
    }

    /**
     * 新增部门
     */
    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        log.info("新增部门: {}", dept);
        return deptService.addDept(dept);
    }

    /**
     * 根据id获取部门信息
     */
    @GetMapping("/{id}")
    // PathVariable: 获取路径中的变量, 如果前端传参与形参名一致, 可以不用写(“参数名”)
    public Result<Dept> getDeptById(@PathVariable Integer id) {
        log.info("根据id获取部门: {}", id);
        return deptService.getDeptById(id);
    }

    /**
     * 修改部门
     */
    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        log.info("修改部门: {}", dept);
        return deptService.updateDept(dept);
    }


}
