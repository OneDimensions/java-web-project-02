package com.onedimension.service.impl;

import com.onedimension.mapper.EmpMapper;
import com.onedimension.pojo.ClazzCountOption;
import com.onedimension.pojo.JobOption;
import com.onedimension.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {

        List<Map<String, Object>> jobDataList = empMapper.countEmpJobData();
        log.info(" 统计员工职位人数: {}", jobDataList);
        JobOption jobOption = new JobOption();
        List<String> posList = jobDataList.stream().map(jobMap -> jobMap.get("pos").toString()).toList();
        List<Integer> dataList = jobDataList.stream().map(jobMap -> Integer.parseInt(jobMap.get("num").toString())).toList();
        jobOption.setJobList(posList);
        jobOption.setDataList(dataList);
        return jobOption;
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.getEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return empMapper.countStudentDegreeData();
    }

    @Override
    public ClazzCountOption getStudentCountData() {
        List<Map<String, Object>> countDataList = empMapper.countStudentData();
        log.info("统计学生人数: {}", countDataList);
        List<Object> classNames = countDataList.stream().map(map -> map.get("className")).toList();
        List<Object> numList = countDataList.stream().map(map -> map.get("num")).toList();

        return new ClazzCountOption(classNames, numList);
    }
}
