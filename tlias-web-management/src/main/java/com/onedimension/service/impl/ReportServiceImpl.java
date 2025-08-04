package com.onedimension.service.impl;

import com.onedimension.mapper.ReportMapper;
import com.onedimension.pojo.JobOption;
import com.onedimension.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public JobOption empJobData() {
        return reportMapper.empJobData();
    }
}
