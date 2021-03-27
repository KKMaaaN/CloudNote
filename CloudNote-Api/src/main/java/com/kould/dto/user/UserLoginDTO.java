package com.kould.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
    private Long id ;
    private String phoneNumber ;
    private String passWord ;
}
