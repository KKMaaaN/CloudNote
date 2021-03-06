package com.kould.service;

import com.kould.dto.crowd.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.user.UserMessageDTO;
import com.kould.service.fallback.CrowdServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "CLOUDNOTE-CROWD-SERVICE", fallbackFactory = CrowdServiceFallbackFactory.class)
public interface ICrowdService {
    String PREFIX = "/cloudnote/crowd/" ;
    @PostMapping(PREFIX + "addCrowd")
    int add(CrowdBaseDTO crowdBaseDTO) ;
    @PostMapping(PREFIX + "removeCrowd")
    int remove(CrowdBaseDTO crowdBaseDTO) ;
    @PostMapping(PREFIX + "editCrowd")
    int edit(CrowdBaseDTO crowdBaseDTO) ;
    @PostMapping(PREFIX + "getCrowdsByCrowdName")
    List<CrowdBaseDTO> findByCrowdName(CrowdBaseDTO crowdBaseDTO,
                                       PageAndSizeDTO pageAndSizeDTO) ;
    @PostMapping(PREFIX + "getCrowdByCrowdId")
    CrowdBaseDTO findByCrowdId(CrowdBaseDTO crowdBaseDTO) ;
    @PostMapping(PREFIX + "getCrowdsOfAll")
    List<CrowdBaseDTO> findAllCrowd(PageAndSizeDTO pageAndSizeDTO) ;
    @PostMapping(PREFIX + "getCrowdsByUserId")
    List<CrowdBaseDTO> findByUserId(UserMessageDTO userMessageDTO,
                                    PageAndSizeDTO pageAndSizeDTO) ;
}
