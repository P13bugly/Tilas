package com.example.tilas.service.impl;

import com.example.tilas.mapper.EmpMapper;
import com.example.tilas.mapper.StuMapper;
import com.example.tilas.pojo.ClazzOption;
import com.example.tilas.pojo.JobOption;
import com.example.tilas.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StuMapper stuMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("number")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGender() {
        return empMapper.countEmpGender();
    }

    @Override
    public List<Map<String, Object>> getStuDegreeData() {
        return stuMapper.countStuDegreeData();
    }

    @Override
    public ClazzOption getStudentCountData() {
        List<Map<String, Object>> list = stuMapper.countStudentCountData();
        List<Object> clazzList = list.stream().map(clazzData -> clazzData.get("clazz")).toList();
        List<Object> dataList = list.stream().map(clazzData -> clazzData.get("number")).toList();
        return new ClazzOption(clazzList, dataList);
    }
}
