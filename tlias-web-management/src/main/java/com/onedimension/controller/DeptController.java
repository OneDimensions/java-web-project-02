package com.onedimension.controller;

import com.onedimension.pojo.Dept;
import com.onedimension.pojo.Result;
import com.onedimension.service.DeptService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RequestMapping在类上注解表示当前类的所有公共路径都是这个
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 获取部门列表
     */
    @GetMapping
    public Result<List<Dept>> depts() {
        return deptService.getDepts();
    }

    /**
     * 删除部门
     * @param id 部门id
     */
    // 前端传递参数与服务端形参名一致, 可以不用写@RequestParam映射
    @DeleteMapping
    public Result deleteDept(Integer id) {
        return deptService.deleteDept(id);
    }

    /**
     * 新增部门
     */
    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        System.out.println(dept);
        return deptService.addDept(dept);
    }

    /**
     * 根据id获取部门信息
     */
    @GetMapping("/{id}")
    // PathVariable: 获取路径中的变量, 如果前端传参与形参名一致, 可以不用写(“参数名”)
    public Result<Dept> getDeptById(@PathVariable Integer id) {
        return deptService.getDeptById(id);
    }

    /**
     * 修改部门
     */
    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {

        return deptService.updateDept(dept);
    }


}
