package com.example.tilas.service;

import com.example.tilas.pojo.Dept;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DeptService {
    /*
     *查询所有部门
     */
    List<Dept> findAll();

    /*
    根据Id删除部门
     */
    void deleteById(Integer id);

    void save(@RequestBody Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
