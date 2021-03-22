package com.kould.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "CLOUD-ZUUL-9501")
public interface ICommentService {
}
