package com.kould.service.fallback;

import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserLoginDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.service.IBlogService;
import com.kould.service.IUserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceFallbackFactory implements FallbackFactory<IUserService> {

    @Override
    public IUserService create(Throwable throwable) {
        System.out.println("开始回退");
        return new IUserService(){

            @Override
            public int add(UserLoginDTO userLoginDTO, UserMessageDTO userMessageDTO) {
                return 0;
            }

            @Override
            public int remove(UserMessageDTO userMessageDTO) {
                return 0;
            }

            @Override
            public int editMessage(UserMessageDTO userMessageDTO) {
                return 0;
            }

            @Override
            public int editLogin(UserLoginDTO userLoginDTO) {
                return 0;
            }

            @Override
            public List<UserMessageDTO> findByUserName(UserMessageDTO userMessageDTO, PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }

            @Override
            public UserMessageDTO findByUserId(UserMessageDTO userMessageDTO) {
                return new UserMessageDTO();
            }

            @Override
            public List<UserMessageDTO> allUserList(PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }
        };
    }
}
