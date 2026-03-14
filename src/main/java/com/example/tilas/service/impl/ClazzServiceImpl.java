package com.example.tilas.service.impl;

import com.example.tilas.mapper.ClazzMapper;
import com.example.tilas.mapper.EmpMapper;
import com.example.tilas.pojo.Clazz;
import com.example.tilas.pojo.ClazzQueryParam;
import com.example.tilas.pojo.Emp;
import com.example.tilas.pojo.PageResult;
import com.example.tilas.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Clazz> list(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        Page<Clazz> list = (Page<Clazz>) clazzMapper.list(clazzQueryParam);
        return new PageResult<>(list.getTotal(), list.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Clazz clazz) {
        Clazz oldClazz = clazzMapper.getInfo(clazz.getId());
        Integer oldMasterId = oldClazz == null ? null : oldClazz.getMasterId();
        Integer newMasterId = clazz.getMasterId();

        //1.更新班级表
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
        //2.更新emps表,将new masterId的job改为班主任,old masterId改为讲师

        //a.判断old masterId是否为空且新旧不一
        if (oldMasterId != null && !Objects.equals(oldMasterId, newMasterId)) {
            Emp oldEmp = new Emp();
            oldEmp.setId(oldMasterId);
            oldEmp.setJob(2);
            oldEmp.setUpdateTime(LocalDateTime.now());
            empMapper.updateById(oldEmp);
        }
        //b.判断new masterId是否null
        if (newMasterId != null) {
            Emp emp = new Emp();
            emp.setId(newMasterId);
            emp.setJob(1);
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.updateById(emp);
        }
    }

    @Override
    public void insert(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getInfo(id);

    }

    @Override
    public List<Clazz> getClaInfo() {
         return clazzMapper.getClaInfo();
    }

}
