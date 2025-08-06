package com.onedimension.mapper;

import com.onedimension.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    // operate_emp_id int unsigned comment '操作人ID',
    // operate_time   datetime comment '操作时间',
    // class_name     varchar(100) comment '操作的类名',
    // method_name    varchar(100) comment '操作的方法名',
    // method_params  varchar(2000) comment '方法参数',
    // return_value   varchar(2000) comment '返回值',
    // cost_time      bigint unsigned comment '方法执行耗时，单位：ms'
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    void insert(OperateLog operateLog);
}
