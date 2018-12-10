package com.manage.frame.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/7
 * Time: 13:47
 */

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.manage.frame.entity")
@ComponentScan(basePackages = {"com.manage.frame"})
public class ManageApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ManageApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}
