package com.kould.po;

import lombok.Data;

import java.util.Date;
@Data
public class CrowdPO {
    private Long id ;
    private String crowdName ;
    private String introduction ;
    private Date createTime ;
    private Date updateTime ;
    private byte[] avatar ;
    private Long ownerId ;
}
