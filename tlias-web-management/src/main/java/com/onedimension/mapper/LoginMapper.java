package com.onedimension.mapper;

import com.onedimension.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    Emp login(Emp emp);
}
