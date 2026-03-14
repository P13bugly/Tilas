package com.example.tilas.controller;

import com.example.tilas.anno.Log;
import com.example.tilas.pojo.Emp;
import com.example.tilas.pojo.EmpQueryParam;
import com.example.tilas.pojo.PageResult;
import com.example.tilas.pojo.Result;
import com.example.tilas.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *员工管理
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询请求参数: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    @Log
    public Result add(@RequestBody Emp emp){
        log.info("新增员工数量:{}",emp);
        empService.add(emp);
        return Result.success();
    }

    @DeleteMapping
    @Log
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除员工:{}", ids);
        empService.deleteById(ids);
        return  Result.success();
    }
    @GetMapping("{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据员工id查询信息:{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }
    @PutMapping
    @Log
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息:{}",emp);
        empService.update(emp);
        return  Result.success();
    }

    @GetMapping("/list")
    public Result listAll(){
        log.info("查询全部员工信息");
        List<Emp> emps = empService.listAll();
        return Result.success(emps);
    }
}
