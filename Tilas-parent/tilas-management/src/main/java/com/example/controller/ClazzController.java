package com.example.controller;

import com.example.anno.Log;
import com.example.tilas.pojo.Clazz;
import com.example.tilas.pojo.ClazzQueryParam;
import com.example.tilas.pojo.PageResult;
import com.example.tilas.pojo.Result;
import com.example.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result list(ClazzQueryParam clazzQueryParam) {
        log.info("查询请求参数:{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.list(clazzQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    @Log
    public Result deleteById(@PathVariable Integer id) {
        log.info("根据id删除班级:{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    @Log
    public Result update(@RequestBody Clazz clazz) {
        log.info("下拉选择班主任列表");
        clazzService.update(clazz);
        return Result.success();
    }

    @PostMapping
    @Log
    public Result insert(@RequestBody Clazz clazz) {
        log.info("新增班级");
        clazzService.insert(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询班级(回显):{}",id);
        Clazz info = clazzService.getInfo(id);
        return Result.success(info);
    }
    @GetMapping("/list")
    public Result getClaInfo(){
        log.info("选择学员的班级");
        List<Clazz> claInfo = clazzService.getClaInfo();
        return Result.success(claInfo);
    }
}
