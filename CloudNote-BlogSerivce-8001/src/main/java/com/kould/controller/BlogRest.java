package com.kould.controller;

import com.kould.dto.BlogBaseDTO;
import com.kould.dto.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.service.IBlogService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogRest {
    @Autowired
    private IBlogService blogService ;

    @Autowired
    private DiscoveryClient client ;

    @RequestMapping("addBlog")
    @HystrixCommand
    public Object addBlog(BlogBaseDTO blogBaseDTO) {
        return this.blogService.add(blogBaseDTO) ;
    }

    @RequestMapping("removeBlog")
    @HystrixCommand
    public Object remove(BlogBaseDTO blogBaseDTO) {
        return this.blogService.remove(blogBaseDTO) ;
    }

    @RequestMapping("editBlog")
    @HystrixCommand
    public Object edit(BlogBaseDTO blogBaseDTO) {
        return this.blogService.edit(blogBaseDTO) ;
    }

    @RequestMapping("getBlogsByBlogTitle")
    @HystrixCommand //Hystrix监控注解
    public Object getBlogsByBlogTitle (BlogBaseDTO blogBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
        return this.blogService.findByBlogTitle(blogBaseDTO, pageAndSizeDTO) ;
    }

    @RequestMapping("getBlogByBlogId")
    @HystrixCommand
    public Object getBlogByBlogId (BlogBaseDTO blogBaseDTO) {
        return this.blogService.findByBlogId(blogBaseDTO) ;
    }

    @RequestMapping("getBlogsOfAll")
    @HystrixCommand
    public Object getBlogsOfAll (PageAndSizeDTO pageAndSizeDTO) {
        return this.blogService.allBlogList(pageAndSizeDTO) ;
    }

    @RequestMapping("getBlogsByUserId")
    @HystrixCommand
    public Object getBlogsByUserId (UserMessageDTO userMessageDTO,
                                   PageAndSizeDTO pageAndSizeDTO) {
        return this.blogService.findByUserId(userMessageDTO, pageAndSizeDTO) ;
    }

    @RequestMapping("getBlogsByCrowdId")
    @HystrixCommand
    public Object getBlogsByCrowdId (CrowdBaseDTO crowdBaseDTO,
                                     PageAndSizeDTO pageAndSizeDTO) {
        return this.blogService.findByCrowdId(crowdBaseDTO, pageAndSizeDTO) ;
    }

    @RequestMapping("discover")
    @HystrixCommand //Hystrix监控注解
    public Object discover() {
        return this.client ;
    }
}
