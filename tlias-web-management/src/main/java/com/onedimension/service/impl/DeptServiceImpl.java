package com.onedimension.service.impl;

import com.onedimension.mapper.DeptMapper;
import com.onedimension.pojo.Dept;
import com.onedimension.pojo.Result;
import com.onedimension.utils.ResultUtil;
import com.onedimension.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Result<List<Dept>> getDepts() {
        List<Dept> depts = deptMapper.getDepts();

        depts.forEach(dept -> {
            System.out.println(dept.getCreateTime());
        });
        return ResultUtil.success(depts);
    }

    @Override
    public Result deleteDept(Integer id) {
        int rows = deptMapper.deleteDept(id);
        if(rows > 0) {
            return ResultUtil.success("删除成功");
        } else {
            return ResultUtil.fail("删除失败, 部门不存在");
        }
    }

    @Override
    public Result addDept(Dept dept) {
        if(dept.getName() == null) {
            return ResultUtil.fail("部门名不能为空");
        }
        int rows = deptMapper.addDept(dept);
        if(rows > 0) {
            return ResultUtil.success("添加成功");
        } else {
            return ResultUtil.fail("添加失败");
        }
    }
}
