package com.kould.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
//@Component
//public class ZuulFallback implements FallbackProvider {
//    @Override
//    public String getRoute() {
//        return "*";
//    }
//
//    @Override
//    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
//        return new ClientHttpResponse() {
//            @Override
//            public HttpStatus getStatusCode() throws IOException {
//                // 返回状态常量
//                return HttpStatus.SERVICE_UNAVAILABLE;
//            }
//
//            @Override
//            public int getRawStatusCode() throws IOException {
//                // 返回状态码，这里为503
//                return HttpStatus.SERVICE_UNAVAILABLE.value();
//            }
//
//            @Override
//            public String getStatusText() throws IOException {
//                // 返回状态码对应的状态短语，这里为"Service Unavailable"
//                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
//            }
//
//            @Override
//            public void close() { }
//
//            @Override
//            public InputStream getBody() throws IOException {
//                // 设置降级信息
//                // String msg = "fallback:" + ConsumerFallback.this.getRoute();
//                String msg = "fallback:" + getRoute();
//                return new ByteArrayInputStream(msg.getBytes());
//            }
//
//            @Override
//            public HttpHeaders getHeaders() {
//                // 设置降级响应头信息
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.APPLICATION_JSON);
//                return headers;
//            }
//        };
//    }
//}
