package com.example.service.impl;

import com.example.mapper.StuMapper;
import com.example.service.StuService;
import com.example.tilas.pojo.PageResult;
import com.example.tilas.pojo.StuQueryParam;
import com.example.tilas.pojo.Student;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper stuMapper;

    @Override
    public PageResult<Student> list(StuQueryParam stuQueryParam) {
        //控制分页参数
        PageHelper.startPage(stuQueryParam.getPage(), stuQueryParam.getPageSize());
        Page<Student> studentPage = (Page<Student>) stuMapper.list(stuQueryParam);
        return new PageResult<Student>(studentPage.getTotal(), studentPage.getResult());
    }

    @Override
    public void insert(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        student.setCreateTime(LocalDateTime.now());
        stuMapper.insert(student);
    }

    @Override
    public Student SearchById(Integer id) {
        return stuMapper.SearchById(id);
    }

    @Override
    public void update(Student student) {
        stuMapper.update(student);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        stuMapper.deleteByIds(ids);
    }

    @Override
    public void disciplinaryAction(Integer id, Integer score) {
        stuMapper.disciplinaryAction(id, score);
    }
}
