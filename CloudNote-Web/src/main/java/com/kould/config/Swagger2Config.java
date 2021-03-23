package com.kould.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.getApiInfo()).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.kould.controller")) //掃描程序包
                .paths(PathSelectors.any()).build() ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("后台系统接口文档")
                .description("Swagger2后台系统在线文档")
                .termsOfServiceUrl("https://github.com/KKMaaaN")
                .license("Kould").version("1.0").build() ;
    }
}
