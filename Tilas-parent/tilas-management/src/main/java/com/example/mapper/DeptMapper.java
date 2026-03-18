package com.example.mapper;

import com.example.tilas.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {

    // 查询所有部门
    List<Dept> findAll();

    // 按 id 删除
    void deleteById(Integer id);

    // 新增
    void insert(Dept dept);

    // 按 id 查询
    Dept getById(Integer id);

    // 修改
    void update(Dept dept);
}
