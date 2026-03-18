package com.example.Aop;

import com.example.mapper.OperateLogMapper;
import com.example.tilas.pojo.OperateLog;
import com.example.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.example.anno.Log)")
    public Object LogOperation(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        //执行方法
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();


        //构建日志对象
        OperateLog operateLog = new OperateLog(null, getCurrentUserId() ,
                null,
                LocalDateTime.now(),
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()),
                String.valueOf(result),
                endTime - startTime
                );
        log.info("生成日志记录对象:{}",operateLog);
        operateLogMapper.insert(operateLog);
        return result;
    }

    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentLocal();
    }
}
