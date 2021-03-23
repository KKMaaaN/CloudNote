package com.kould.controller;

import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserLoginDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.service.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "/User User通用接口")
@RestController
public class UserRest {
    @Autowired
    private IUserService userService ;

    @Autowired
    private DiscoveryClient client ;

    @ApiOperation(value = "增加User", notes = "增加UserLoginDTO和UserMessageDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User的Id",
            required = false, readOnly = true,dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "User的名字",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "gender", value = "User的性别",
                    required = false, dataType = "Short"),
            @ApiImplicitParam(name = "password", value = "User的密码",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "User的邮箱",
                    required = false, dataType = "String"),
            @ApiImplicitParam(name = "phoneNumber", value = "User的电话号码",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "nationality", value = "User的国籍",
                    required = false, dataType = "String"),
            @ApiImplicitParam(name = "introduction", value = "User的简介",
                    required = false, dataType = "String")
    })
    @PostMapping("addUser")
    @HystrixCommand
    public Object add(UserLoginDTO userLoginDTO, UserMessageDTO userMessageDTO) {
        return this.userService.add(userLoginDTO, userMessageDTO) ;
    }

    @ApiOperation(value = "删除User", notes = "删除UserLoginDTO和UserMessageDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User的Id",
                    required = true, dataType = "Long"),
    })
    @PostMapping("removeUser")
    @HystrixCommand
    public Object remove(UserMessageDTO userMessageDTO) {
        return this.userService.remove(userMessageDTO) ;
    }

    @ApiOperation(value = "修改UserMessage", notes = "修改UserMessageDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User的Id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "User的名字",
                    required = false, dataType = "String"),
            @ApiImplicitParam(name = "gender", value = "User的性别",
                    required = false, dataType = "Short"),
            @ApiImplicitParam(name = "email", value = "User的邮箱",
                    required = false, dataType = "String"),
            @ApiImplicitParam(name = "phoneNumber", value = "User的电话号码",
                    required = false,readOnly = true, dataType = "String"),
            @ApiImplicitParam(name = "nationality", value = "User的国籍",
                    required = false, dataType = "String"),
            @ApiImplicitParam(name = "introduction", value = "User的简介",
                    required = false, dataType = "String")
    })
    @PostMapping("editUserMessage")
    @HystrixCommand
    public Object editUserMessage(UserMessageDTO userMessageDTO) {
        return this.userService.editMessage(userMessageDTO) ;
    }

    @ApiOperation(value = "修改UserLogin", notes = "修改UserLoginDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User的Id",
                    required = true,dataType = "Long"),
            @ApiImplicitParam(name = "phoneNumber", value = "User的电话号",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "User的密码",
                    required = true, dataType = "String")
    })
    @PostMapping("editUserLogin")
    @HystrixCommand
    public Object editUserLogin(UserLoginDTO userLoginDTO) {
        return this.userService.editLogin(userLoginDTO) ;
    }

    @ApiOperation(value = "以User的name搜索User", notes = "以UserName的形式模糊搜索UserMessageDTO对象，" +
            "肯呢个返回一个或多个UserMessageDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "User的名字",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getUsersByUserName")
    @HystrixCommand
    public Object getUsersByUserName (UserMessageDTO userMessageDTO,
                                     PageAndSizeDTO pageAndSizeDTO) {
        return this.userService.findByUserName(userMessageDTO, pageAndSizeDTO) ;
    }

    @ApiOperation(value = "以User的Id搜索User", notes = "以UserId的形式准确搜索UserMessageDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User的Id",
                    required = true, dataType = "Long")
    })
    @PostMapping("getUserByUserId")
    @HystrixCommand
    public Object getUserByUserId (UserMessageDTO userMessageDTO) {
        return this.userService.findByUserId(userMessageDTO) ;
    }

    @ApiOperation(value = "搜索所有的User", notes = "直接返回所有的UserMessageDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getUsersOfAll")
    @HystrixCommand
    public Object getUsersOfAll (PageAndSizeDTO pageAndSizeDTO) {
        return this.userService.allUserList(pageAndSizeDTO) ;
    }

    @PostMapping("discover")
    @HystrixCommand //Hystrix监控注解
    public Object discover() {
        return this.client ;
    }
}
