package com.kould.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kould.dto.BlogBaseDTO;
import com.kould.dto.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.mapper.BlogMapper;
import com.kould.po.BlogPO;
import com.kould.service.IBlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogMapper blogMapper ;

    @Override
    public int add(BlogBaseDTO blogBaseDTO) {
        return this.blogMapper.insert(dtoToPo(blogBaseDTO)) ;
    }

    @Override
    public int remove(BlogBaseDTO blogBaseDTO) {
        return this.blogMapper.deleteById(blogBaseDTO.getId()) ;
    }

    @Override
    public int edit(BlogBaseDTO blogBaseDTO) {
        return this.blogMapper.updateById(dtoToPo(blogBaseDTO)) ;
    }

    @Override
    public List<BlogBaseDTO> findByBlogTitle(BlogBaseDTO blogBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
        Page<BlogPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize()) ;
        QueryWrapper<BlogPO> wrapper = new QueryWrapper<>() ;
        wrapper.like("title",blogBaseDTO.getTitle());
        List<BlogPO> POList = this.blogMapper.selectPage(page, wrapper).getRecords() ;
        return posToDtos(POList);
    }

    @Override
    public BlogBaseDTO findByBlogId(BlogBaseDTO blogBaseDTO) {
        return poToDto(this.blogMapper.selectById(blogBaseDTO.getId()));
    }

    @Override
    public List<BlogBaseDTO> allBlogList(PageAndSizeDTO pageAndSizeDTO) {
        Page<BlogPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize()) ;
        return posToDtos(this.blogMapper.selectPage(page, null).getRecords());
    }

    @Override
    public List<BlogBaseDTO> findByUserId(UserMessageDTO userMessageDTO, PageAndSizeDTO pageAndSizeDTO) {
        Page<BlogPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize()) ;
        QueryWrapper<BlogPO> wrapper = new QueryWrapper<>();
        wrapper.eq("owner_id",userMessageDTO.getId()) ;
        return posToDtos(this.blogMapper.selectPage(page, wrapper).getRecords());
    }

    @Override
    public List<BlogBaseDTO> findByCrowdId(CrowdBaseDTO crowdBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
        Page<BlogPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize()) ;
        QueryWrapper<BlogPO> wrapper = new QueryWrapper<>();
        wrapper.eq("crowd_id",crowdBaseDTO.getId()) ;
        return posToDtos(this.blogMapper.selectPage(page, wrapper).getRecords());
    }


    private BlogPO dtoToPo(BlogBaseDTO blogBaseDTO) {
        BlogPO blogPO = new BlogPO();
        BeanUtils.copyProperties(blogBaseDTO,blogPO);
        return blogPO ;
    }

    private BlogBaseDTO poToDto(BlogPO blogPO) {
        BlogBaseDTO blogBaseDTO = new BlogBaseDTO();
        BeanUtils.copyProperties(blogPO, blogBaseDTO);
        return blogBaseDTO ;
    }

    private List<BlogBaseDTO> posToDtos(List<BlogPO> POList) {
        List<BlogBaseDTO> DTOList = new ArrayList<>() ;
        for (BlogPO blogPO : POList ) {
            BlogBaseDTO baseDTO = new BlogBaseDTO() ;
            BeanUtils.copyProperties(blogPO, baseDTO);
            DTOList.add(baseDTO) ;
        }
        return DTOList;
    }
}
