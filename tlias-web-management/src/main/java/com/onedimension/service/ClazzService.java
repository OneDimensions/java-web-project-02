package com.onedimension.service;

import com.onedimension.pojo.Clazz;
import com.onedimension.pojo.ClazzQueryParams;
import com.onedimension.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    PageResult<Clazz> page(ClazzQueryParams clazzQueryParams);

    void save(Clazz clazz);

    void delete(Integer id);

    Clazz getClazzById(Integer id);

    void update(Clazz clazz);

    List<Clazz> list();
}
