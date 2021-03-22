package com.kould.controller;

import com.kould.dto.*;
import com.kould.service.ICommentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentRest {
    @Autowired
    private ICommentService commentService ;

    @Autowired
    private DiscoveryClient client ;

    @RequestMapping("addComment")
    @HystrixCommand
    public Object add(CommentBaseDTO commentBaseDTO) {
        return this.commentService.add(commentBaseDTO) ;
    }

    @RequestMapping("removeComment")
    @HystrixCommand
    public Object remove(CommentBaseDTO commentBaseDTO) {
        return this.commentService.remove(commentBaseDTO) ;
    }

    @RequestMapping("editComment")
    @HystrixCommand
    public Object edit(CommentBaseDTO commentBaseDTO) {
        return this.commentService.edit(commentBaseDTO) ;
    }

    @RequestMapping("getCommentsByCommentContent")
    @HystrixCommand
    public Object getCommentsByCommentContent(CommentBaseDTO commentBaseDTO,
                                             PageAndSizeDTO pageAndSizeDTO) {
        return this.commentService.findByCommentContent(commentBaseDTO, pageAndSizeDTO) ;
    }

    @RequestMapping("getCommentByCommentId")
    @HystrixCommand
    public Object getCommentByCommentId(CommentBaseDTO commentBaseDTO) {
        return this.commentService.findByCommentId(commentBaseDTO) ;
    }

    @RequestMapping("getCommentsOfAll")
    @HystrixCommand
    public Object getCommentsOfAll(PageAndSizeDTO pageAndSizeDTO) {
        return this.commentService.allCommentList(pageAndSizeDTO) ;
    }

    @RequestMapping("getCommentsByUserId")
    @HystrixCommand
    public Object getCommentsByUserId(UserMessageDTO userMessageDTO, PageAndSizeDTO pageAndSizeDTO) {
        return this.commentService.findByUserId(userMessageDTO, pageAndSizeDTO) ;
    }

    @RequestMapping("getCommentsByBlogId")
    @HystrixCommand
    public Object getCommentsByBlogId(BlogBaseDTO blogBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
        return this.commentService.findByBlogId(blogBaseDTO, pageAndSizeDTO) ;
    }

    @RequestMapping("discover")
    @HystrixCommand //Hystrix监控注解
    public Object discover() {
        return this.client ;
    }
}
