package com.kould.controller;

import com.kould.dto.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.service.ICrowdService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrowdRest {
    @Autowired
    private ICrowdService crowdService ;

    @Autowired
    private DiscoveryClient client ;

    @PostMapping("addCrowd")
    @HystrixCommand
    public Object add(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.add(crowdBaseDTO) ;
    }

    @PostMapping("removeCrowd")
    @HystrixCommand
    public Object remove(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.remove(crowdBaseDTO) ;
    }

    @PostMapping("editCrowd")
    @HystrixCommand
    public Object edit(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.edit(crowdBaseDTO) ;
    }

    @PostMapping("getCrowdsByCrowdName")
    @HystrixCommand //Hystrix监控注解
    public Object getCrowdsByCrowdName (CrowdBaseDTO crowdBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
        return this.crowdService.findByCrowdName(crowdBaseDTO, pageAndSizeDTO) ;
    }

    @PostMapping("getCrowdByCrowdId")
    @HystrixCommand
    public Object getCrowdByCrowdId (CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.findByCrowdId(crowdBaseDTO) ;
    }

    @PostMapping("getCrowdsOfAll")
    @HystrixCommand
    public Object getCrowdOfAll (PageAndSizeDTO pageAndSizeDTO) {
        return this.crowdService.findAllCrowd(pageAndSizeDTO) ;
    }

    @PostMapping("getCrowdsByUserId")
    @HystrixCommand
    public Object getCrowdsByUserId (UserMessageDTO userMessageDTO,
                                    PageAndSizeDTO pageAndSizeDTO) {
        return this.crowdService.findByUserId(userMessageDTO, pageAndSizeDTO) ;
    }

    @PostMapping("discover")
    @HystrixCommand //Hystrix监控注解
    public Object discover() {
        return this.client ;
    }
}
