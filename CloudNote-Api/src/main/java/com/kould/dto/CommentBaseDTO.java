package com.kould.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentBaseDTO implements Serializable {
    private Long id ;
    private String content ;
    private Date createTime ;
    private Date updateTime ;
    private Long blogId ;
    private Long ownerId ;
}
