package com.example.tilas.exception;

import com.example.tilas.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/*
 *全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handler (Exception exception){
        log.error("程序出错",exception);
        return  Result.error("出错了,请联系管理员");
    }

    @ExceptionHandler
    public Result handlerDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错了");
        String message = e.getMessage();
        String errMsg = message.substring(message.indexOf("Duplicate entry"));
        String[] errs = errMsg.split(" ");
        return Result.error("对不起"+errs[2]+"已存在");
    }

    @ExceptionHandler
    public Result handlerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        log.error("数据库约束异常", e);
        return handleFkViolation(e.getMessage());
    }

    @ExceptionHandler
    public Result handlerDataIntegrityViolationException(DataIntegrityViolationException e){
        log.error("数据库约束异常", e);
        String message = e.getMostSpecificCause().getMessage();
        if (message == null) {
            message = e.getMessage();
        }
        return handleFkViolation(message);
    }

    private Result handleFkViolation(String message){
        if (message == null) {
            return Result.error("数据约束异常");
        }
        if (message.contains("fk_student_clazz_id")) {
            return Result.error("对不起, 该班级下有学生, 不能直接删除");
        }
        if (message.contains("fk_emp_dept_id")) {
            return Result.error("对不起, 该部门下有员工, 无法删除");
        }
        return Result.error("数据约束异常");
    }
}
