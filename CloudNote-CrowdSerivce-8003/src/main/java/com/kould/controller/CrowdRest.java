package com.kould.controller;

import com.kould.dto.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.service.ICrowdService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "/crowd Crowd通用接口")
@RestController
public class CrowdRest {
    @Autowired
    private ICrowdService crowdService ;

    @Autowired
    private DiscoveryClient client ;

    @ApiOperation(value = "增加Crowd", notes = "增加CrowdBaseDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Crowd的Id",
                    required = false, readOnly = true,dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "Crowd的名字",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "introduction", value = "Crowd的简介",
                    required = false, dataType = "String"),
            @ApiImplicitParam(name = "ownerId", value = "Crowd的所有者Id",
                    required = true, dataType = "Long")
    })
    @PostMapping("addCrowd")
    @HystrixCommand
    public Object add(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.add(crowdBaseDTO) ;
    }

    @ApiOperation(value = "删除Crowd", notes = "删除CrowdBaseDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Crowd的所有者Id",
                    required = true, dataType = "Long")
    })
    @PostMapping("removeCrowd")
    @HystrixCommand
    public Object remove(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.remove(crowdBaseDTO) ;
    }

    @ApiOperation(value = "修改Crowd", notes = "修改CrowdBaseDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Crowd的所有者Id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "Crowd的名字",
                    required = false, dataType = "String"),
            @ApiImplicitParam(name = "introduction", value = "Crowd的简介",
                    required = false, dataType = "String"),
            @ApiImplicitParam(name = "ownerId", value = "Crowd的所有者Id",
                    required = false, dataType = "Long")
    })
    @PostMapping("editCrowd")
    @HystrixCommand
    public Object edit(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.edit(crowdBaseDTO) ;
    }

    @ApiOperation(value = "以Crowd的名字搜索Crowd", notes = "以Crowd名字的形式模糊搜索CrowdBaseDTO对象，" +
            "并返回一个或多个CrowdBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Crowd的名字",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getCrowdsByCrowdName")
    @HystrixCommand //Hystrix监控注解
    public Object getCrowdsByCrowdName (CrowdBaseDTO crowdBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
        return this.crowdService.findByCrowdName(crowdBaseDTO, pageAndSizeDTO) ;
    }


    @ApiOperation(value = "以Crowd的Id搜索Crowd", notes = "以CrowdId的形式准确搜索CrowdBaseDTO对象，" +
            "并返回一个或多个CrowdBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Crowd的所有者Id",
                    required = true, dataType = "Long")
    })
    @PostMapping("getCrowdByCrowdId")
    @HystrixCommand
    public Object getCrowdByCrowdId (CrowdBaseDTO crowdBaseDTO) {
        return this.crowdService.findByCrowdId(crowdBaseDTO) ;
    }

    @ApiOperation(value = "搜索所有的Crowd", notes = "直接返回所有的CrowdBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getCrowdsOfAll")
    @HystrixCommand
    public Object getCrowdOfAll (PageAndSizeDTO pageAndSizeDTO) {
        return this.crowdService.findAllCrowd(pageAndSizeDTO) ;
    }

    @ApiOperation(value = "以Crowd的所属用户Id搜索Crowd", notes = "以UserId的形式准确搜索CrowdBaseDTO对象，" +
            "并返回一个或多个CrowdBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User的Id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
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
