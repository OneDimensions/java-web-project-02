package com.onedimension.service;

import com.onedimension.pojo.ClazzCountOption;
import com.onedimension.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {


    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    ClazzCountOption getStudentCountData();
}
