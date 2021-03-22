package com.kould.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kould.po.UserPO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserPO> {
}
