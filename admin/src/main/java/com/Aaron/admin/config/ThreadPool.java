package com.Aaron.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPool {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor()
    {
        return new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
