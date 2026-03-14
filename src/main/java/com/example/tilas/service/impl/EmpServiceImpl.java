package com.example.tilas.service.impl;

import com.example.tilas.mapper.EmpExprMapper;
import com.example.tilas.mapper.EmpMapper;
import com.example.tilas.pojo.*;
import com.example.tilas.service.EmpService;
import com.example.tilas.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;


    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        Page<Emp> pageResult = (Page<Emp>) empMapper.list(empQueryParam);
        return new PageResult<Emp>(pageResult.getTotal(), pageResult.getResult());
    }

    @Transactional
    @Override
    public void add(Emp emp) {
        if (emp.getPassword() == null || emp.getPassword().isBlank()) {
            emp.setPassword("123456");
        }
        //1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //2.保存员工工作经历基本信息
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(EmpExpr -> EmpExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteById(List<Integer> ids) {
        //批量删除员工基本信息
        empMapper.deleteByIds(ids);
        //批量删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //更新员工工作经历信息

        //1.删除old的工作经历信息
        empExprMapper.deleteByEmpIds(Collections.singletonList(emp.getId()));
        //2.更新上传新的
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr-> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }

    }

    @Override
    public List<Emp> listAll() {
        return empMapper.listAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.mapper 获取员工信息
        Emp e = empMapper.searchBasedOnUsernameAndPassword(emp);
        if(e!=null){
            log.info("登录成功,员工信息:{}",e);
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String token = JwtUtils.generateJwt(claims);
            return new LoginInfo(e.getId(),e.getUsername(),token,e.getName());
        }
        return null;
    }
}
