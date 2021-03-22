package com.kould.controller;

import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserLoginDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.service.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRest {
    @Autowired
    private IUserService userService ;

    @Autowired
    private DiscoveryClient client ;

    @RequestMapping("addUser")
    @HystrixCommand
    public Object addUser(UserLoginDTO userLoginDTO, UserMessageDTO userMessageDTO) {
        return this.userService.add(userLoginDTO, userMessageDTO) ;
    }

    @RequestMapping("removeUser")
    @HystrixCommand
    public Object remove(UserMessageDTO userMessageDTO) {
        return this.userService.remove(userMessageDTO) ;
    }

    @RequestMapping("editUserMessage")
    @HystrixCommand
    public Object editUserMessage(UserMessageDTO userMessageDTO) {
        return this.userService.editMessage(userMessageDTO) ;
    }

    @RequestMapping("editUserLogin")
    @HystrixCommand
    public Object editUserLogin(UserLoginDTO userLoginDTO) {
        return this.userService.editLogin(userLoginDTO) ;
    }

    @RequestMapping("getUsersByUserName")
    @HystrixCommand
    public Object getUsersByUserName (UserMessageDTO userMessageDTO,
                                     PageAndSizeDTO pageAndSizeDTO) {
        return this.userService.findByUserName(userMessageDTO, pageAndSizeDTO) ;
    }

    @RequestMapping("getUserByUserId")
    @HystrixCommand
    public Object getUserByUserId (UserMessageDTO userMessageDTO) {
        return this.userService.findByUserId(userMessageDTO) ;
    }

    @RequestMapping("getUsersOfAll")
    @HystrixCommand
    public Object getUsersOfAll (PageAndSizeDTO pageAndSizeDTO) {
        return this.userService.allUserList(pageAndSizeDTO) ;
    }

    @RequestMapping("discover")
    @HystrixCommand //Hystrix监控注解
    public Object discover() {
        return this.client ;
    }
}
