package com.onedimension.mapper;

import com.onedimension.pojo.JobOption;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {

    // 5.2 员工职位人数统计
    JobOption empJobData();

}
