package com.Aaron.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.Aaron")
@MapperScan("com.Aaron.mapper")
public class webApplication {
    public static void main(String[] args) {
        SpringApplication.run(webApplication.class, args);
    }
}
