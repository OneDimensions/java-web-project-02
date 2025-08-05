package com.onedimension.mapper;

import com.onedimension.pojo.Clazz;
import com.onedimension.pojo.ClazzQueryParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> page(ClazzQueryParams clazzQueryParams);

    void save(Clazz clazz);

    void delete(Integer id);

    Clazz getClazzById(Integer id);

    void update(Clazz clazz);

    List<Clazz> list();
}
