package com.kould.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kould.dto.*;
import com.kould.dto.comment.CommentBaseDTO;
import com.kould.dto.blog.BlogBaseDTO;
import com.kould.dto.user.UserMessageDTO;
import com.kould.mapper.CommentMapper;
import com.kould.po.CommentPO;
import com.kould.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper ;
    
    @Override
    public int add(CommentBaseDTO commentBaseDTO) {
        commentBaseDTO.setId(null);
        return this.commentMapper.insert(dtoToPo(commentBaseDTO));
    }

    @Override
    public int remove(CommentBaseDTO commentBaseDTO) {
        return this.commentMapper.deleteById(commentBaseDTO.getId());
    }

    @Override
    public int edit(CommentBaseDTO commentBaseDTO) {
        return this.commentMapper.updateById(dtoToPo(commentBaseDTO));
    }

    @Override
    public List<CommentBaseDTO> findByCommentContent(CommentBaseDTO commentBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
        Page<CommentPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize()) ;
        QueryWrapper<CommentPO> wrapper = new QueryWrapper<>();
        wrapper.like("content", commentBaseDTO.getContent());
        return posToDtos(this.commentMapper.selectPage(page, wrapper).getRecords()) ;
    }

    @Override
    public CommentBaseDTO findByCommentId(CommentBaseDTO commentBaseDTO) {
        return poToDto(this.commentMapper.selectById(commentBaseDTO)) ;
    }

    @Override
    public List<CommentBaseDTO> allCommentList(PageAndSizeDTO pageAndSizeDTO) {
        Page<CommentPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize());
        return posToDtos(this.commentMapper.selectPage(page, null).getRecords());
    }

    @Override
    public List<CommentBaseDTO> findByBlogId(BlogBaseDTO blogBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
        Page<CommentPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize());
        QueryWrapper<CommentPO> wrapper = new QueryWrapper<CommentPO>();
        wrapper.eq("blog_id", blogBaseDTO.getId()) ;
        return posToDtos(this.commentMapper.selectPage(page, wrapper).getRecords());
    }

    @Override
    public List<CommentBaseDTO> findByUserId(UserMessageDTO userMessageDTO, PageAndSizeDTO pageAndSizeDTO) {
        Page<CommentPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize());
        QueryWrapper<CommentPO> wrapper = new QueryWrapper<CommentPO>();
        wrapper.eq("owner_id", userMessageDTO.getId()) ;
        return posToDtos(this.commentMapper.selectPage(page, wrapper).getRecords());
    }

    private CommentPO dtoToPo(CommentBaseDTO commentBaseDTO) {
        CommentPO commentPO = new CommentPO();
        BeanUtils.copyProperties(commentBaseDTO,commentPO);
        return commentPO ;
    }

    private CommentBaseDTO poToDto(CommentPO commentPO) {
        CommentBaseDTO CommentBaseDTO = new CommentBaseDTO();
        BeanUtils.copyProperties(commentPO, CommentBaseDTO);
        return CommentBaseDTO ;
    }

    private List<CommentBaseDTO> posToDtos(List<CommentPO> POList) {
        List<CommentBaseDTO> DTOList = new ArrayList<>() ;
        for (CommentPO commentPO : POList ) {
            CommentBaseDTO baseDTO = new CommentBaseDTO() ;
            BeanUtils.copyProperties(commentPO, baseDTO);
            DTOList.add(baseDTO) ;
        }
        return DTOList;
    }
}
