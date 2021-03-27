package com.kould.controller;

import com.kould.dto.user.UserPackageDTO;
import com.kould.service.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Object add(@RequestBody UserPackageDTO userPackageDTO) {
        return this.userService.add(userPackageDTO.getUserLoginDTO(), userPackageDTO.getUserMessageDTO()) ;
    }

    @ApiOperation(value = "删除User", notes = "删除UserLoginDTO和UserMessageDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User的Id",
                    required = true, dataType = "Long"),
    })
    @PostMapping("removeUser")
    @HystrixCommand
    public Object remove(@RequestBody UserPackageDTO userPackageDTO) {
        return this.userService.remove(userPackageDTO.getUserMessageDTO()) ;
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
    public Object editUserMessage(@RequestBody UserPackageDTO userPackageDTO) {
        return this.userService.editMessage(userPackageDTO.getUserMessageDTO()) ;
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
    public Object editUserLogin(@RequestBody UserPackageDTO userPackageDTO) {
        return this.userService.editLogin(userPackageDTO.getUserLoginDTO()) ;
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
    public Object getUsersByUserName (@RequestBody UserPackageDTO userPackageDTO) {
        return this.userService.findByUserName(userPackageDTO.getUserMessageDTO(), userPackageDTO.getPageAndSizeDTO()) ;
    }

    @ApiOperation(value = "以User的Id搜索User", notes = "以UserId的形式准确搜索UserMessageDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User的Id",
                    required = true, dataType = "Long")
    })
    @PostMapping("getUserByUserId")
    @HystrixCommand
    public Object getUserByUserId (@RequestBody UserPackageDTO userPackageDTO) {
        return this.userService.findByUserId(userPackageDTO.getUserMessageDTO()) ;
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
    public Object getUsersOfAll (@RequestBody UserPackageDTO userPackageDTO) {
        return this.userService.allUserList(userPackageDTO.getPageAndSizeDTO()) ;
    }

    @PostMapping("discover")
    @HystrixCommand //Hystrix监控注解
    public Object discover() {
        return this.client ;
    }
}
