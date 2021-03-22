package com.kould.controller;

import com.kould.dto.BlogBaseDTO;
import com.kould.dto.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.service.IBlogService;
import com.kould.service.ICrowdService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrowdRest {
    @Autowired
    private ICrowdService crowdService ;

    @Autowired
    private DiscoveryClient client ;

    @RequestMapping("addCrowd")
    @HystrixCommand
    public Object addBlog(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.add(crowdBaseDTO) ;
    }

    @RequestMapping("removeCrowd")
    @HystrixCommand
    public Object remove(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.remove(crowdBaseDTO) ;
    }

    @RequestMapping("editCrowd")
    @HystrixCommand
    public Object edit(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.edit(crowdBaseDTO) ;
    }

    @RequestMapping("getCrowdsByCrowdName")
    @HystrixCommand //Hystrix监控注解
    public Object getCrowdsByCrowdName (CrowdBaseDTO crowdBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
        return this.crowdService.findByCrowdName(crowdBaseDTO, pageAndSizeDTO) ;
    }

    @RequestMapping("getCrowdByCrowdId")
    @HystrixCommand
    public Object getCrowdByCrowdId (CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.findByCrowdId(crowdBaseDTO) ;
    }

    @RequestMapping("getCrowdsOfAll")
    @HystrixCommand
    public Object getCrowdOfAll (PageAndSizeDTO pageAndSizeDTO) {
        return this.crowdService.findAllCrowd(pageAndSizeDTO) ;
    }

    @RequestMapping("getCrowdsByUserId")
    @HystrixCommand
    public Object getCrowdsByUserId (UserMessageDTO userMessageDTO,
                                    PageAndSizeDTO pageAndSizeDTO) {
        return this.crowdService.findByUserId(userMessageDTO, pageAndSizeDTO) ;
    }

    @RequestMapping("discover")
    @HystrixCommand //Hystrix监控注解
    public Object discover() {
        return this.client ;
    }
}
