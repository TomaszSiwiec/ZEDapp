package com.zedapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZedappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZedappApplication.class, args);
    }
}
