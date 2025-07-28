package com.onedimension.mapper;

import com.onedimension.pojo.Emp;
import com.onedimension.pojo.EmpQueryParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    // -----原始分页实现
    // List<Emp> page(Integer start, Integer pageSize);
    //
    // /**
    //  * 获取总数
    //  */
    // @Select("select count(*) from emp e left join dept d on e.dept_id = d.id;")
    // Long total();

    // -----mybatis分页实现 pagehelper

    List<Emp> page(EmpQueryParams empQueryParams);

}
