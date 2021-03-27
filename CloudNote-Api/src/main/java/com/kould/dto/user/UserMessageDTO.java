package com.kould.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserMessageDTO implements Serializable {
    private Long id ;
    private String name ;
    private short gender ;
    private String email ;
    private String phoneNumber ;
    private String nationality ;
    private String introduction ;
    private Date createTime ;
    private Date updateTime ;
    private byte[] avatar ;
}
