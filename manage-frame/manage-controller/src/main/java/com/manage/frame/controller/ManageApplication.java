package com.manage.frame.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by
 * User: Liuwq
 * Date: 2018/12/7
 * Time: 13:47
 */

@SpringBootApplication
@MapperScan(basePackages = "com.manage.frame.dao")
@ComponentScan(basePackages = {"com.manage.frame"})
public class ManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

}
