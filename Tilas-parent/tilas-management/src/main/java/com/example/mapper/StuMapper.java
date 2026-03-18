package com.example.mapper;

import com.example.tilas.pojo.StuQueryParam;
import com.example.tilas.pojo.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StuMapper {




    List<Student> list(StuQueryParam stuQueryParam);

    void insert(Student student);

    Student SearchById(Integer id);

    void update(Student student);

    void deleteByIds(List<Integer> ids);

    void disciplinaryAction(Integer id, Integer score);
    @MapKey("name")
    List<Map<String, Object>> countStuDegreeData();
    @MapKey("clazz")
    List<Map<String,Object>> countStudentCountData();
}
