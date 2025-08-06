package com.onedimension.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onedimension.mapper.OperateLogMapper;
import com.onedimension.pojo.OperateLog;
import com.onedimension.pojo.PageParams;
import com.onedimension.pojo.PageResult;
import com.onedimension.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogServiceImpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(PageParams pageParams) {

        PageHelper.startPage(pageParams.getPage(), pageParams.getPageSize());

        Page<OperateLog> page = (Page<OperateLog>) operateLogMapper.page();

        return new PageResult<>(page.getTotal(), page.getResult());
    }
}
