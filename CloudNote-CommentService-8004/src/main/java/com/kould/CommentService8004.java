package com.kould;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.kould.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker //熔断机制启动
@EnableHystrix  //Hystrix支持启动
@EnableSwagger2
public class CommentService8004 {
    public static void main(String[] args) {
        SpringApplication.run(CommentService8004.class, args) ;
    }
}
