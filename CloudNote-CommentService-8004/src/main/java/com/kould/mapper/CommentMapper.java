package com.kould.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kould.po.CommentPO;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<CommentPO> {
}
