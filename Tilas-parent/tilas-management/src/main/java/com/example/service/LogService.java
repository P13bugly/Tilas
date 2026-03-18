package com.example.service;

import com.example.tilas.pojo.OperateLog;
import com.example.tilas.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
