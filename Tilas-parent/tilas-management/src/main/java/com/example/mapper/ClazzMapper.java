package com.example.mapper;

import com.example.tilas.pojo.Clazz;
import com.example.tilas.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void update( Clazz clazz);

    void insert(Clazz clazz);

    Clazz getInfo(Integer id);

    List<Clazz> getClaInfo();
}
