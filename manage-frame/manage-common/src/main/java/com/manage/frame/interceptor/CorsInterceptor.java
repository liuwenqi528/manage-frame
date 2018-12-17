package com.manage.frame.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 设置支持跨域请求
 *
 * @author
 */
@Slf4j
public class CorsInterceptor implements HandlerInterceptor {


    /**
     * 设置跨域
     *
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @param o        方法信息(MethodInfo)
     * @return 是否继续往下执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("corsInterceptor");
        log.info("cors请求地址：{}", request.getServletPath());
        log.info("cors请求方式：{}", request.getMethod());
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            response.setStatus(200);
            return false;
        }
        String origin = request.getHeader("Origin");

        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT,HEAD,TRACE,PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,content-type");
        //表示是否允许发送Cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return true;
    }

//    private String getHeaders(HttpServletRequest request) {
//        Enumeration<String> headerNames = request.getHeaderNames();
//        StringBuffer sb = new StringBuffer();
//        while(headerNames.hasMoreElements()){
//            sb.append(headerNames.nextElement()).append(",");
//        }
//        String headers = sb.substring(0,sb.length());
//        return headers;
//    }

}
