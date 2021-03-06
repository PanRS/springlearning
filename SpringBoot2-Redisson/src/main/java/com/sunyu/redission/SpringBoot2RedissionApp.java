package com.sunyu.redission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yu on 2018/11/05.
 */
@SpringBootApplication
@MapperScan("com.sunyu.redission.dao")
public class SpringBoot2RedissionApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2RedissionApp.class, args);
    }
}
