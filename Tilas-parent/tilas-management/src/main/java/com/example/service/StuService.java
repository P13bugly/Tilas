package com.example.service;

import com.example.tilas.pojo.PageResult;
import com.example.tilas.pojo.StuQueryParam;
import com.example.tilas.pojo.Student;

import java.util.List;

public interface StuService {
    /**
     * 分页查询
     *
     * @param stuQueryParam stu查询参数
     * @return {@link PageResult }<{@link Student }>
     */
    PageResult<Student> list(StuQueryParam stuQueryParam);

    /**
     * 插入学生
     *
     * @param student 学生
     */
    void insert(Student student);

    /**
     * 按id回显
     *
     * @param id ID
     * @return {@link Student }
     */
    Student SearchById(Integer id);

    /**
     * 修改学生信息
     *
     * @param student 学生
     */
    void update(Student student);

    /**
     * 按id批量删除
     *
     * @param ids 标识符
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 违纪处理
     *
     * @param id    ID
     * @param score 得分
     */
    void disciplinaryAction(Integer id, Integer score);
}
