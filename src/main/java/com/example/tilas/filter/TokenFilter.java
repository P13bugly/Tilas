package com.example.tilas.filter;

import com.example.tilas.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
//@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        //1.获取请求路径
        String requestURI = request.getRequestURI();
        //2.判断是否是登录请求("/login"),放行
        if(requestURI.contains("/login")){
            log.info("登录请求,放行");
            chain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3.获取request header token
        String token = request.getHeader("token");
        //4.判断token是否存在,不存在,返回401状态码
        if(token==null|| token.isEmpty()){
            log.info("令牌为空,响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //5.parseJWT,校验失败,401,成功放行
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("令牌非法,响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6.通过放行
        log.info("令牌合法,放行");
        chain.doFilter(servletRequest,servletResponse);

    }
}
