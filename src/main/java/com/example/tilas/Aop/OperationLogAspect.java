package com.example.tilas.Aop;

import com.example.tilas.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import com.example.tilas.mapper.OperateLogMapper;
import com.example.tilas.pojo.OperateLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.example.tilas.anno.Log)")
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
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes servletRequestAttributes)) {
            return null;
        }

        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("token");
        if (token == null || token.isBlank()) {
            return null;
        }

        Claims claims = JwtUtils.parseJwt(token);
        Object id = claims.get("id");
        if (id instanceof Integer integerId) {
            return integerId;
        }
        if (id instanceof Number number) {
            return number.intValue();
        }
        return id == null ? null : Integer.valueOf(id.toString());
    }
}
