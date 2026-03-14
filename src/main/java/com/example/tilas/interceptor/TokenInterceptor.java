package com.example.tilas.interceptor;

import com.example.tilas.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
 *令牌校验的拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    //在目标资源方法运行之前运行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1.获取请求路径
//        String requestURI = request.getRequestURI();
//        //2.判断是否是登录请求("/login"),放行
//        if (requestURI.contains("/login")) {
//            log.info("登录请求,放行");
//            return true;
//        }
        //3.获取request header token
        String token = request.getHeader("token");
        //4.判断token是否存在,不存在,返回401状态码
        if (token == null || token.isEmpty()) {
            log.info("令牌为空,响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //5.parseJWT,校验失败,401,成功放行
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("令牌非法,响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //6.通过放行
        log.info("令牌合法,放行");
        return true;
    }


}
