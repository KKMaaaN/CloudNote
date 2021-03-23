package com.kould.service.fallback;

import com.kould.dto.BlogBaseDTO;
import com.kould.dto.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.service.IBlogService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlogServiceFallbackFactory implements FallbackFactory<IBlogService> {

    @Override
    public IBlogService create(Throwable throwable) {
        return new IBlogService() {
            @Override
            public int add(BlogBaseDTO blogBaseDTO) {
                return 0;
            }

            @Override
            public int remove(BlogBaseDTO blogBaseDTO) {
                return 0;
            }

            @Override
            public int edit(BlogBaseDTO blogBaseDTO) {
                return 0;
            }

            @Override
            public List<BlogBaseDTO> findByBlogTitle(BlogBaseDTO blogBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }

            @Override
            public BlogBaseDTO findByBlogId(BlogBaseDTO blogBaseDTO) {
                return new BlogBaseDTO();
            }

            @Override
            public List<BlogBaseDTO> allBlogList(PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }

            @Override
            public List<BlogBaseDTO> findByUserId(UserMessageDTO userMessageDTO, PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }

            @Override
            public List<BlogBaseDTO> findByCrowdId(CrowdBaseDTO crowdBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }
        };
    }
}
