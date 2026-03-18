package com.example.controller;

import com.example.tilas.pojo.PageResult;
import com.example.tilas.pojo.Result;
import com.example.tilas.pojo.StuQueryParam;
import com.example.tilas.pojo.Student;
import com.example.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StuService stuService;

    @GetMapping
    public Result list(StuQueryParam stuQueryParam) {
        log.info("查询请求参数:{}", stuQueryParam);
        PageResult<Student> pageResult = stuService.list(stuQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result insert(@RequestBody Student student) {
        log.info("添加学生:{}", student);
        stuService.insert(student);
        return Result.success();
    }

    @GetMapping("{id}")
    public Result SearchById(@PathVariable Integer id) {
        log.info("根据id查询:{}", id);
        Student student = stuService.SearchById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员:{}", student);
        stuService.update(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        log.info("批量删除学生:{}", ids);
        stuService.deleteByIds(ids);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result disciplinaryAction(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪学生处理:{},{}",id,score);
        stuService.disciplinaryAction(id,score);
        return Result.success();
    }
}
