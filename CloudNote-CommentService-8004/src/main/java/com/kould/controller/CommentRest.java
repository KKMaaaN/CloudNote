package com.kould.controller;

import com.kould.dto.blog.BlogPackageDTO;
import com.kould.dto.comment.CommentPackageDTO;
import com.kould.dto.user.UserPackageDTO;
import com.kould.service.ICommentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "/Comment Comment通用接口")
@RestController
public class CommentRest {
    @Autowired
    private ICommentService commentService ;

    @Autowired
    private DiscoveryClient client ;

    @ApiOperation(value = "增加Comment", notes = "增加CommentBaseDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Comment的Id",
                    required = false, readOnly = true,dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "Comment的正文",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "ownerId", value = "Comment的所有者Id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "blogId", value = "Comment的所属BlogId",
                    required = true, dataType = "Long")
    })
    @PostMapping("addComment")
    @HystrixCommand
    public Object add(@RequestBody CommentPackageDTO commentPackageDTO) {
        return this.commentService.add(commentPackageDTO.getCommentBaseDTO()) ;
    }

    @ApiOperation(value = "删除Comment", notes = "删除CommentBaseDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Comment的id",
                    required = true, dataType = "Long")
    })
    @PostMapping("removeComment")
    @HystrixCommand
    public Object remove(@RequestBody CommentPackageDTO commentPackageDTO) {
        return this.commentService.remove(commentPackageDTO.getCommentBaseDTO()) ;
    }

    @ApiOperation(value = "修改Comment", notes = "修改CommentBaseDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Comment的id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "Comment的正文",
                    required = true, dataType = "String")
    })
    @PostMapping("editComment")
    @HystrixCommand
    public Object edit(@RequestBody CommentPackageDTO commentPackageDTO) {
        return this.commentService.edit(commentPackageDTO.getCommentBaseDTO()) ;
    }

    @ApiOperation(value = "以Comment的正文搜索Comment", notes = "以Comment正文的形式模糊查询搜索CommentBaseDTO对象，" +
            "并返回一个或多个BlogBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "Comment的正文",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getCommentsByCommentContent")
    @HystrixCommand
    public Object getCommentsByCommentContent(@RequestBody CommentPackageDTO commentPackageDTO) {
        return this.commentService.findByCommentContent(commentPackageDTO.getCommentBaseDTO(),
                commentPackageDTO.getPageAndSizeDTO()) ;
    }

    @ApiOperation(value = "以Comment的Id搜索Comment", notes = "以CommentId的形式准确查询搜索CommentBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Comment的id",
                    required = true, dataType = "Long")
    })
    @PostMapping("getCommentByCommentId")
    @HystrixCommand
    public Object getCommentByCommentId(@RequestBody CommentPackageDTO commentPackageDTO) {
        return this.commentService.findByCommentId(commentPackageDTO.getCommentBaseDTO()) ;
    }

    @ApiOperation(value = "搜索所有的Comment", notes = "直接返回所有的CommentBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "Comment的正文",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getCommentsOfAll")
    @HystrixCommand
    public Object getCommentsOfAll(@RequestBody CommentPackageDTO commentPackageDTO) {
        return this.commentService.allCommentList(commentPackageDTO.getPageAndSizeDTO()) ;
    }

    @ApiOperation(value = "以Comment的所属用户Id搜索Comment", notes = "以UserId的形式准确查询搜索CommentBaseDTO对象，" +
            "并返回一个或多个CommentBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User的Id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getCommentsByUserId")
    @HystrixCommand
    public Object getCommentsByUserId(@RequestBody UserPackageDTO userPackageDTO) {
        return this.commentService.findByUserId(userPackageDTO.getUserMessageDTO(), userPackageDTO.getPageAndSizeDTO()) ;
    }

    @ApiOperation(value = "以Comment的所属BlogId搜索Comment", notes = "以BlogId的形式准确查询搜索CommentBaseDTO对象，" +
            "并返回一个或多个CommentBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Blog的Id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getCommentsByBlogId")
    @HystrixCommand
    public Object getCommentsByBlogId(@RequestBody BlogPackageDTO blogPackageDTO) {
        return this.commentService.findByBlogId(blogPackageDTO.getBlogBaseDTO(),
                blogPackageDTO.getPageAndSizeDTO()) ;
    }

    @PostMapping("discover")
    @HystrixCommand //Hystrix监控注解
    public Object discover() {
        return this.client ;
    }
}
