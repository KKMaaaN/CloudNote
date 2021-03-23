package com.kould.controller;

import com.kould.dto.BlogBaseDTO;
import com.kould.dto.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.service.IBlogService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

@Api(tags = "/blog Blog通用接口")
@RestController
public class BlogRest {
    @Autowired
    private IBlogService blogService ;

    @Autowired
    private DiscoveryClient client ;

    @ApiOperation(value = "增加Blog", notes = "增加BlogBaseDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Blog的Id",
                    required = false, readOnly = true,dataType = "Long"),
            @ApiImplicitParam(name = "title", value = "Blog的标题",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "Blog的正文",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "ownerId", value = "Blog的所有者Id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "crowdId", value = "Blog的所属板块Id",
                    required = false, dataType = "Long")
    })
    @PostMapping("addBlog")
    @HystrixCommand
    public Object add(BlogBaseDTO blogBaseDTO) {
        return this.blogService.add(blogBaseDTO) ;
    }

    @ApiOperation(value = "删除Blog", notes = "删除BlogBaseDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Blog的Id",
                    required = true, dataType = "String")
    })
    @PostMapping("removeBlog")
    @HystrixCommand
    public Object remove(BlogBaseDTO blogBaseDTO) {
        return this.blogService.remove(blogBaseDTO) ;
    }

    @ApiOperation(value = "修改Blog", notes = "修改BlogBaseDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Blog的Id",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "title", value = "Blog的标题",
                    required = false, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "Blog的正文",
                    required = false, dataType = "String"),
            @ApiImplicitParam(name = "ownerId", value = "Blog的所有者Id",
                    required = false, dataType = "Long"),
            @ApiImplicitParam(name = "crowdId", value = "Blog的所属板块Id",
                    required = false, dataType = "Long")
    })
    @PostMapping("editBlog")
    @HystrixCommand
    public Object edit(BlogBaseDTO blogBaseDTO) {
        return this.blogService.edit(blogBaseDTO) ;
    }

    @ApiOperation(value = "以Blog的标题搜索Blog", notes = "以Blog标题的形式模糊搜索BlogBaseDTO对象，" +
            "并返回一个或多个BlogBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "Blog的标题",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getBlogsByBlogTitle")
    @HystrixCommand //Hystrix监控注解
    public Object getBlogsByBlogTitle (BlogBaseDTO blogBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
        return this.blogService.findByBlogTitle(blogBaseDTO, pageAndSizeDTO) ;
    }


    @ApiOperation(value = "以Blog的Id搜索Blog", notes = "以BlogId的形式准确搜索BlogBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Blog的Id",
                    required = true, dataType = "String")
    })
    @PostMapping("getBlogByBlogId")
    @HystrixCommand
    public Object getBlogByBlogId (BlogBaseDTO blogBaseDTO) {
        return this.blogService.findByBlogId(blogBaseDTO) ;
    }

    @ApiOperation(value = "搜索所有的Blog", notes = "直接返回所有的BlogBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getBlogsOfAll")
    @HystrixCommand
    public Object getBlogsOfAll (PageAndSizeDTO pageAndSizeDTO) {
        return this.blogService.allBlogList(pageAndSizeDTO) ;
    }

    @ApiOperation(value = "以用户的Id搜索所属的Blog", notes = "以UserId的形式准确搜索所属的BlogBaseDTO对象，" +
            "可能返回一个或多个BlogBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User的Id",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getBlogsByUserId")
    @HystrixCommand
    public Object getBlogsByUserId (UserMessageDTO userMessageDTO,
                                   PageAndSizeDTO pageAndSizeDTO) {
        return this.blogService.findByUserId(userMessageDTO, pageAndSizeDTO) ;
    }

    @ApiOperation(value = "以板块的Id搜索所属的Blog", notes = "以CrowdId的形式准确搜索所属的BlogBaseDTO对象，" +
            "可能返回一个或多个BlogBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Crowd的Id",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "index", value = "页数"
                    ,required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "stepSize", value = "步数"
                    ,required = true,dataType = "Integer")
    })
    @PostMapping("getBlogsByCrowdId")
    @HystrixCommand
    public Object getBlogsByCrowdId (CrowdBaseDTO crowdBaseDTO,
                                     PageAndSizeDTO pageAndSizeDTO) {
        return this.blogService.findByCrowdId(crowdBaseDTO, pageAndSizeDTO) ;
    }

    @PostMapping("discover")
    @HystrixCommand //Hystrix监控注解
    public Object discover() {
        return this.client ;
    }
}
