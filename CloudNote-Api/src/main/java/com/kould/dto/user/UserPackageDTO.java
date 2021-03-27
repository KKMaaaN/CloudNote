package com.kould.dto.user;

import com.kould.dto.PageAndSizeDTO;
import lombok.Data;

@Data
public class UserPackageDTO {
    private UserMessageDTO userMessageDTO ;
    private UserLoginDTO userLoginDTO ;
    private PageAndSizeDTO pageAndSizeDTO ;
}
