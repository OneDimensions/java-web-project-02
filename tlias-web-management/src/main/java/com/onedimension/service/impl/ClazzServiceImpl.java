package com.onedimension.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onedimension.mapper.ClazzMapper;
import com.onedimension.pojo.Clazz;
import com.onedimension.pojo.ClazzQueryParams;
import com.onedimension.pojo.PageResult;
import com.onedimension.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParams clazzQueryParams) {
        log.info("分页查询: {}", clazzQueryParams);
        PageHelper.startPage(clazzQueryParams.getPage(), clazzQueryParams.getPageSize());
        List<Clazz> pageList = clazzMapper.page(clazzQueryParams);
        pageList.forEach(clazz -> {
            LocalDate beginDate = clazz.getBeginDate();
            LocalDate endDate = clazz.getEndDate();
            if (beginDate != null && endDate != null) {
                LocalDate now = LocalDate.now();
                if (now.isBefore(beginDate)) {
                    clazz.setStatus("未开班");
                } else if (now.isEqual(beginDate) || now.isEqual(endDate) || now.isAfter(beginDate) && now.isBefore(endDate)) {
                    clazz.setStatus("已开班");
                } else {
                    clazz.setStatus("已结课");
                }
            }
        });
        Page p = (Page) pageList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Clazz clazz) {
        LocalDateTime now = LocalDateTime.now();
        clazz.setCreateTime(now);
        clazz.setUpdateTime(now);
        clazzMapper.save(clazz);
    }

    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }

    @Override
    public Clazz getClazzById(Integer id) {
        return clazzMapper.getClazzById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> list() {
        return clazzMapper.list();
    }
}
