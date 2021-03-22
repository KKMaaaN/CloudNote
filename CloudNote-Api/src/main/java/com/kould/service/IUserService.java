package com.kould.service;

import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserLoginDTO;
import com.kould.dto.UserMessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "CLOUDNOTE-ZUUL-9501")
public interface IUserService {
    String PREFIX = "/cloudnote/user/" ;
    @PostMapping(PREFIX + "addUser")
    int add(UserLoginDTO userLoginDTO, UserMessageDTO userMessageDTO) ;
    @PostMapping(PREFIX + "removeUser")
    int remove(UserMessageDTO userMessageDTO) ;
    @PostMapping(PREFIX + "editUser")
    int editMessage(UserMessageDTO userMessageDTO) ;
    @PostMapping(PREFIX + "editUser")
    int editLogin(UserLoginDTO userLoginDTO) ;
    @PostMapping(PREFIX + "getUsersByUserName")
    List<UserMessageDTO> findByUserName(UserMessageDTO userMessageDTO,
                                        PageAndSizeDTO pageAndSizeDTO) ;
    @PostMapping(PREFIX + "getUserByUserId")
    UserMessageDTO findByUserId(UserMessageDTO userMessageDTO) ;
    @PostMapping(PREFIX + "getAllUsers")
    List<UserMessageDTO> allUserList(PageAndSizeDTO pageAndSizeDTO) ;
}
