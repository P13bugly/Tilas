package com.example.controller;

import com.example.anno.Log;
import com.example.tilas.pojo.Dept;
import com.example.tilas.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping
    public Result list() {
        log.info("查询部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping
    @Log
    public Result delete(Integer id) {
        log.info("根据id删除部门, id: {}" , id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    @Log
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门, dept: {}" , dept);
        deptService.save(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询, id: {}" , id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
    @PutMapping
    @Log
    public Result update(@RequestBody Dept dept){
        log.info("修改部门, dept: {}" , dept);
        deptService.update(dept);
        return Result.success();
    }
}
