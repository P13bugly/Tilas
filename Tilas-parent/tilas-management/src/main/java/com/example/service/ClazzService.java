package com.example.service;

import com.example.tilas.pojo.Clazz;
import com.example.tilas.pojo.ClazzQueryParam;
import com.example.tilas.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 列表
     *
     * @param clazzQueryParam clazz查询参数
     * @return {@link PageResult }<{@link Clazz }>
     */
    PageResult<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 按id删除
     *
     * @param id ID
     */
    void deleteById(Integer id);


    /**
     * 修改班级信息
     *
     * @param clazz 班级
     */
    void update(Clazz clazz);

    /**
     * 添加班级
     *
     * @param clazz 班级
     */
    void insert(Clazz clazz);

    /**
     * 根据id查询班级信息
     *
     * @param id ID
     * @return {@link Clazz }
     */
    Clazz getInfo( Integer id);

    /**
     * 获取班级信息
     *
     * @return {@link List }<{@link Clazz }>
     */
    List<Clazz> getClaInfo();
}
