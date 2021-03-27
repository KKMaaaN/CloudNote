package com.kould.service.fallback;

import com.kould.dto.blog.BlogBaseDTO;
import com.kould.dto.comment.CommentBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.user.UserMessageDTO;
import com.kould.service.ICommentService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentSerivceFallbackFactory implements FallbackFactory<ICommentService> {
    @Override
    public ICommentService create(Throwable throwable) {
        return new ICommentService() {
            @Override
            public int add(CommentBaseDTO CommentBaseDTO) {
                return 0;
            }

            @Override
            public int remove(CommentBaseDTO CommentBaseDTO) {
                return 0;
            }

            @Override
            public int edit(CommentBaseDTO CommentBaseDTO) {
                return 0;
            }

            @Override
            public List<CommentBaseDTO> findByCommentContent(CommentBaseDTO CommentBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }

            @Override
            public CommentBaseDTO findByCommentId(CommentBaseDTO CommentBaseDTO) {
                return new CommentBaseDTO();
            }

            @Override
            public List<CommentBaseDTO> allCommentList(PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }

            @Override
            public List<CommentBaseDTO> findByBlogId(BlogBaseDTO blogBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }

            @Override
            public List<CommentBaseDTO> findByUserId(UserMessageDTO userMessageDTO, PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }
        };
    }
}
