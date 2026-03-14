package com.example.tilas.controller;

import com.example.tilas.pojo.OperateLog;
import com.example.tilas.pojo.PageResult;
import com.example.tilas.pojo.Result;
import com.example.tilas.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/page")
    public Result page(@RequestParam Integer page, @RequestParam Integer pageSize) {
        log.info("分页查询操作日志, page: {}, pageSize: {}", page, pageSize);
        PageResult<OperateLog> pageResult = logService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
