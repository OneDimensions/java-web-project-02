package com.onedimension.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JobOption {
    /**
     * 部门列表
     */
    List<String> jobList;
    /**
     * 部门人数
     */
    List<Integer> dataList;
}
