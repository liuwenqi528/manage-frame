package com.manage.frame.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * WebMvc 配置
 * Created by
 * User: Liuwq
 * Date: 2018/12/18
 * Time: 10:45
 */
@Slf4j
//@Configuration
public class WebFileConfiguration {

    /**
     * 文件上传
     * @return
     */
//    @Bean("multipartResolver")
    public MultipartResolver multipartResolver() {
        log.info("文件上传");
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxInMemorySize(4096);
        commonsMultipartResolver.setMaxUploadSize(200*1024);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return  commonsMultipartResolver;
    }
}
