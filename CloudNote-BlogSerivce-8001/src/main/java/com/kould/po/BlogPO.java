package com.kould.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "blog")
public class BlogPO implements Serializable {
    @TableId(type = IdType.ID_WORKER)
    private Long id ;
    private String title ;
    private String introduction ;
    private String content ;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime ;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime ;
    private byte[] image ;
    private Long ownerId ;
    private Long crowdId ;

    @TableLogic
    private short deleted ;
}
