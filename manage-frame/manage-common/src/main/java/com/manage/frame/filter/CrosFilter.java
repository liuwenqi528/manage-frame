package com.manage.frame.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Order(-99999)
@Component
@WebFilter(filterName = "CrosFilter", urlPatterns = "/**")
public class CrosFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        log.debug("{} 允许 跨域访问", request.getRequestURI());
        log.info("corsInterceptor");
        log.info("cors请求地址：{}", request.getServletPath());
        log.info("cors请求方式：{}", request.getMethod());
        String method = request.getMethod();

        String origin = request.getHeader("Origin");

        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT,HEAD,TRACE,PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,content-type");
        //表示是否允许发送Cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equals(method.toUpperCase())) {
            response.setStatus(200);
            return;
        }
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
