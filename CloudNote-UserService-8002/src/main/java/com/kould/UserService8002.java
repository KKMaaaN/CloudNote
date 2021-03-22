package com.kould;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@MapperScan("com.kould.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker //熔断机制启动
@EnableHystrix  //Hystrix支持启动
public class UserService8002 {
    public static void main(String[] args) {
        SpringApplication.run(UserService8002.class, args) ;
    }
}
