package com.example.controller;

import com.example.tilas.pojo.ClazzOption;
import com.example.tilas.pojo.JobOption;
import com.example.tilas.pojo.Result;
import com.example.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计各个职位的员工人数");
        JobOption empJobData = reportService.getEmpJobData();
        return Result.success(empJobData);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGender() {
        log.info("统计员工的性别信息");
        List<Map<String, Object>> empGender = reportService.getEmpGender();
        return Result.success(empGender);
    }

    @GetMapping("/studentDegreeData")
    public Result getStuDegreeData() {
        log.info("统计班级学生学历信息");
        List<Map<String, Object>> stuDegreeData = reportService.getStuDegreeData();
        return Result.success(stuDegreeData);
    }

    @GetMapping("/studentCountData")
    public  Result getStudentCountData(){
        log.info("统计各班学生信息");
        ClazzOption studentCountData = reportService.getStudentCountData();
        return Result.success(studentCountData);
    }

}
