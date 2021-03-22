package com.kould.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "comment")
public class CommentPO implements Serializable {
    @TableId(type = IdType.ID_WORKER)
    private Long id ;
    private String content ;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime ;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime ;
    private Long blogId ;
    private Long ownerId ;

    @TableLogic
    private short deleted;
}
