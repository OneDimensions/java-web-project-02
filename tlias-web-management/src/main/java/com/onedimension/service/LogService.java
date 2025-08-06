package com.onedimension.service;

import com.onedimension.pojo.OperateLog;
import com.onedimension.pojo.PageParams;
import com.onedimension.pojo.PageResult;

public interface LogService {

    PageResult<OperateLog> page(PageParams pageParams);

}
