package com.kould.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@TableName(value = "crowd")
public class CrowdPO implements Serializable {
    @TableId(type = IdType.ID_WORKER)
    private Long id ;
    private String name ;
    private String introduction ;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime ;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime ;
    private byte[] avatar ;
    private Long ownerId ;

    @TableLogic
    private short deleted;
}
