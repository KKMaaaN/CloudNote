package com.kould.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BlogBaseDTO implements Serializable {
    private Long id ;
    private String title ;
    private String introduction ;
    private String content ;
    private Date createTime ;
    private Date updateTime ;
    private byte[] image ;
    private Long ownerId ;
    private Long crowdId ;
}