package com.kould;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class Turbine9101 {
    public static void main(String[] args) {
        SpringApplication.run(Turbine9101.class ,args) ;
    }
}
