package com.kould.service.fallback;

import com.kould.dto.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.service.ICrowdService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrowdServiceFallbackFactory implements FallbackFactory<ICrowdService> {
    @Override
    public ICrowdService create(Throwable throwable) {
        return new ICrowdService() {
            @Override
            public int add(CrowdBaseDTO crowdBaseDTO) {
                return 0;
            }

            @Override
            public int remove(CrowdBaseDTO crowdBaseDTO) {
                return 0;
            }

            @Override
            public int edit(CrowdBaseDTO crowdBaseDTO) {
                return 0;
            }

            @Override
            public List<CrowdBaseDTO> findByCrowdName(CrowdBaseDTO crowdBaseDTO, PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }

            @Override
            public CrowdBaseDTO findByCrowdId(CrowdBaseDTO crowdBaseDTO) {
                return new CrowdBaseDTO();
            }

            @Override
            public List<CrowdBaseDTO> findAllCrowd(PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }

            @Override
            public List<CrowdBaseDTO> findByUserId(UserMessageDTO userMessageDTO, PageAndSizeDTO pageAndSizeDTO) {
                return new ArrayList<>();
            }
        } ;
    }
}
