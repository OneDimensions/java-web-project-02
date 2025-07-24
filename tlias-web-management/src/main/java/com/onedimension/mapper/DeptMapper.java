package com.onedimension.mapper;

import com.onedimension.pojo.Dept;
import com.onedimension.pojo.Result;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 获取部门列表
     *
     * @return 部门列表
     */
    // 如果数据库字段名和实体类属性名不一致, 需要进行数据映射
    // 方式一
    // Results: 指定数据映射
    // @Results({
    //         @Result(property = "createTime", column = "create_time"),
    //         @Result(property = "updateTime", column = "update_time")
    // })
    // @Select("select id, name, create_time, update_time from dept order by update_time desc")
    // 方式二: 直接sql起别名
    // @Select("select id, name, create_time createTime, update_time  updateTime from dept order by update_time desc")
    // 方式三: 开启mybatis的自动映射
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> getDepts();


    /**
     * 删除部门
     *
     * @param id 部门id
     */
    @Delete("delete from dept where id = #{id}")
    Integer deleteDept(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values (#{name}, now(), now())")
    Integer addDept(Dept dept);
}
