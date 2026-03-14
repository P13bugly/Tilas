package com.example.tilas.service.impl;

import com.example.tilas.mapper.OperateLogMapper;
import com.example.tilas.pojo.OperateLog;
import com.example.tilas.pojo.PageResult;
import com.example.tilas.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    private final OperateLogMapper operateLogMapper;

    public LogServiceImpl(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> operateLogs = operateLogMapper.list();
        Page<OperateLog> pageResult = (Page<OperateLog>) operateLogs;
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }
}
