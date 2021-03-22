package com.kould.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "user")
public class UserPO implements Serializable {
    @TableId(type = IdType.ID_WORKER)
    private Long id ;
    private String name ;
    private short gender ;
    private String email ;
    private String phoneNumber ;
    private String passWord ;
    private String nationality ;
    private String introduction ;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime ;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime ;
    private byte[] avatar ;

    @TableLogic
    private short deleted ;
}
