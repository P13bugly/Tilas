package com.example.tilas.service;

import com.example.tilas.pojo.ClazzOption;
import com.example.tilas.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 获取emp职位数据
     *
     * @return {@link JobOption }
     */
    JobOption getEmpJobData();

    /**
     * 获取emp性别数据
     *
     * @return {@link List }<{@link Map }<{@link String },{@link Object }>>
     */
    List<Map<String,Object>> getEmpGender();

    /**
     * 获取stu学位数据
     *
     * @return {@link List }<{@link Map }<{@link String }, {@link Object }>>
     */
    List<Map<String, Object>> getStuDegreeData();

    /**
     * 获取各班学生人数数据
     *
     * @return {@link List }<{@link Map }<{@link String }, {@link Object }>>
     */
    ClazzOption getStudentCountData();
}
