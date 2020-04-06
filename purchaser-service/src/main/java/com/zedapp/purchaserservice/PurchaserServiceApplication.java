package com.zedapp.purchaserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PurchaserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaserServiceApplication.class, args);
    }

}
