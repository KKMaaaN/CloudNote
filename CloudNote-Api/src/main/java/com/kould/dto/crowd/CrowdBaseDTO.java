package com.kould.dto.crowd;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CrowdBaseDTO implements Serializable {
    private Long id ;
    private String name ;
    private String introduction ;
    private Date createTime ;
    private Date updateTime ;
    private byte[] avatar ;
    private Long ownerId ;
}
