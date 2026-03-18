package com.example.service;

import com.example.tilas.pojo.Emp;
import com.example.tilas.pojo.EmpQueryParam;
import com.example.tilas.pojo.LoginInfo;
import com.example.tilas.pojo.PageResult;

import java.util.List;



public interface EmpService {


    /**
     * 页
     *
     * @param empQueryParam emp查询参数
     * @return {@link PageResult }<{@link Emp }>
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    void add(Emp emp);

    /**
     * 删除
     *
     * @param ids 标识符
     */
    void deleteById(List<Integer> ids);

    /**
     * 获取信息
     *
     * @param id ID
     * @return {@link Emp }
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     *
     * @param emp 员工
     */
    void update(Emp emp);

    /**
     * 查询全部员工信息
     *
     * @return {@link List }<{@link Emp }>
     */
    List<Emp> listAll();


    /**
     * 登录
     *
     * @param emp 员工
     * @return {@link LoginInfo }
     */
    LoginInfo login(Emp emp);
}
