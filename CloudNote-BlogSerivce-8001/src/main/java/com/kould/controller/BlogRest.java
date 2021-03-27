package com.kould.controller;

import com.kould.dto.blog.BlogPackageDTO;
import com.kould.dto.crowd.CrowdPackageDTO;
import com.kould.dto.user.UserPackageDTO;
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
    public Object add(@RequestBody BlogPackageDTO blogPackageDTO) {
        return this.blogService.add(blogPackageDTO.getBlogBaseDTO()) ;
    }

    @ApiOperation(value = "删除Blog", notes = "删除BlogBaseDTO对象，成功返回1，失败返回0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Blog的Id",
                    required = true, dataType = "String")
    })
    @PostMapping("removeBlog")
    @HystrixCommand
    public Object remove(@RequestBody BlogPackageDTO blogPackageDTO) {
        return this.blogService.remove(blogPackageDTO.getBlogBaseDTO()) ;
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
    public Object edit(@RequestBody BlogPackageDTO blogPackageDTO) {
        return this.blogService.edit(blogPackageDTO.getBlogBaseDTO()) ;
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
    public Object getBlogsByBlogTitle (@RequestBody BlogPackageDTO blogPackageDTO) {
        System.out.println(blogPackageDTO);
        return this.blogService.findByBlogTitle(blogPackageDTO.getBlogBaseDTO(), blogPackageDTO.getPageAndSizeDTO()) ;
    }


    @ApiOperation(value = "以Blog的Id搜索Blog", notes = "以BlogId的形式准确搜索BlogBaseDTO对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Blog的Id",
                    required = true, dataType = "String")
    })
    @PostMapping("getBlogByBlogId")
    @HystrixCommand
    public Object getBlogByBlogId (@RequestBody BlogPackageDTO blogPackageDTO) {
        return this.blogService.findByBlogId(blogPackageDTO.getBlogBaseDTO()) ;
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
    public Object getBlogsOfAll (@RequestBody BlogPackageDTO blogPackageDTO) {
        return this.blogService.allBlogList(blogPackageDTO.getPageAndSizeDTO()) ;
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
    public Object getBlogsByUserId (@RequestBody UserPackageDTO userPackageDTO) {
        return this.blogService.findByUserId(userPackageDTO.getUserMessageDTO(), userPackageDTO.getPageAndSizeDTO()) ;
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
    public Object getBlogsByCrowdId (@RequestBody CrowdPackageDTO crowdPackageDTO) {
        return this.blogService.findByCrowdId(crowdPackageDTO.getCrowdBaseDTO(), crowdPackageDTO.getPageAndSizeDTO()) ;
    }

    @PostMapping("discover")
    @HystrixCommand //Hystrix监控注解
    public Object discover() {
        return this.client ;
    }
}
