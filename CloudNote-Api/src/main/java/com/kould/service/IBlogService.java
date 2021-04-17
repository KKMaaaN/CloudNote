package com.kould.service;

import com.kould.dto.blog.BlogBaseDTO;
import com.kould.dto.crowd.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.user.UserMessageDTO;
import com.kould.service.fallback.BlogServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "CLOUDNOTE-BLOG-SERVICE", fallbackFactory = BlogServiceFallbackFactory.class)
public interface IBlogService {
    String PREFIX = "/cloudnote/blog/" ;
    @PostMapping(PREFIX + "addBlog")
    int add(BlogBaseDTO blogBaseDTO) ;
    @PostMapping(PREFIX + "removeBlog")
    int remove(BlogBaseDTO blogBaseDTO) ;
    @PostMapping(PREFIX + "editBlog")
    int edit(BlogBaseDTO blogBaseDTO) ;
    @PostMapping(PREFIX + "getBlogsByBlogTitle")
    List<BlogBaseDTO> findByBlogTitle(BlogBaseDTO blogBaseDTO,
                                      PageAndSizeDTO pageAndSizeDTO) ;
    @PostMapping(PREFIX + "getBlogByBlogId")
    BlogBaseDTO findByBlogId(BlogBaseDTO blogBaseDTO) ;
    @PostMapping(PREFIX + "getAllBlogs")
    List<BlogBaseDTO> allBlogList(PageAndSizeDTO pageAndSizeDTO) ;
    @PostMapping(PREFIX + "getBlogsByUserId")
    List<BlogBaseDTO> findByUserId(UserMessageDTO userMessageDTO,
                                   PageAndSizeDTO pageAndSizeDTO) ;
    @PostMapping(PREFIX + "getBlogsByCrowdId")
    List<BlogBaseDTO> findByCrowdId(CrowdBaseDTO crowdBaseDTO,
                                    PageAndSizeDTO pageAndSizeDTO) ;
}
