package com.kould.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

// 添加文档来源
@Configuration
@Primary
public class Swagger2DocConfig implements SwaggerResourcesProvider{
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("cloudnote-user-service", "/user/v2/api-docs", "1.0"));
        resources.add(swaggerResource("cloudnote-blog-service", "/blog/v2/api-docs", "1.0"));
        resources.add(swaggerResource("cloudnote-crowd-service", "/crowd/v2/api-docs", "1.0"));
        resources.add(swaggerResource("cloudnote-comment-service", "/comment/v2/api-docs", "1.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
