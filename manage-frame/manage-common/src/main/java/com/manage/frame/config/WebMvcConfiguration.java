package com.manage.frame.config;

import com.manage.frame.interceptor.AuthInterceptor;
import com.manage.frame.interceptor.CorsInterceptor;
import com.manage.frame.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/7
 * Time: 13:19
 */
@Slf4j
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 支持跨域
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedHeaders("*")
//                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "OPTIONS", "PATCH")
//                .maxAge(3600);
//    }
    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("添加拦截器~~~~~~~~");
//        registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/system/login", "/system/logout");
//        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**").excludePathPatterns("/system/login", "/system/logout");
    }

}
