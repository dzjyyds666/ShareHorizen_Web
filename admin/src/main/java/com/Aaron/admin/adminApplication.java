package com.Aaron.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.Aaron")
@MapperScan("com.Aaron.mapper")
public class adminApplication {

    public static void main(String[] args) {
        SpringApplication.run(adminApplication.class, args);
    }
}
