package com.example.service.impl;

import com.example.mapper.OperateLogMapper;
import com.example.tilas.pojo.OperateLog;
import com.example.tilas.pojo.PageResult;
import com.example.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> operateLogs = operateLogMapper.list();
        Page<OperateLog> pageResult = (Page<OperateLog>) operateLogs;
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }
}
