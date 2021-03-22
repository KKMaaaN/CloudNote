package com.kould.service;

import com.kould.dto.BlogBaseDTO;
import com.kould.dto.CommentBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserMessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "CLOUD-ZUUL-9501")
public interface ICommentService {
    String PREFIX = "/cloudnote/comment/" ;
    @PostMapping(PREFIX + "addComment")
    int add(CommentBaseDTO CommentBaseDTO) ;
    @PostMapping(PREFIX + "removeComment")
    int remove(CommentBaseDTO CommentBaseDTO) ;
    @PostMapping(PREFIX + "editComment")
    int edit(CommentBaseDTO CommentBaseDTO) ;
    @PostMapping(PREFIX + "getCommentsByCommentTitle")
    List<CommentBaseDTO> findByCommentContent(CommentBaseDTO CommentBaseDTO,
                                      PageAndSizeDTO pageAndSizeDTO) ;
    @PostMapping(PREFIX + "getCommentByCommentId")
    CommentBaseDTO findByCommentId(CommentBaseDTO CommentBaseDTO) ;
    @PostMapping(PREFIX + "getAllComments")
    List<CommentBaseDTO> allCommentList(PageAndSizeDTO pageAndSizeDTO) ;
    @PostMapping(PREFIX + "getCommentsByBlogId")
    List<CommentBaseDTO> findByBlogId(BlogBaseDTO blogBaseDTO,
                                      PageAndSizeDTO pageAndSizeDTO) ;
    @PostMapping(PREFIX + "getCommentsByUserId")
    List<CommentBaseDTO> findByUserId(UserMessageDTO userMessageDTO,
                                      PageAndSizeDTO pageAndSizeDTO) ;
}
