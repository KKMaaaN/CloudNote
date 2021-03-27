package com.kould.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kould.dto.crowd.CrowdBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.user.UserMessageDTO;
import com.kould.mapper.CrowdMapper;
import com.kould.po.CrowdPO;
import com.kould.service.ICrowdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrowdServiceImpl implements ICrowdService {
    @Autowired
    private CrowdMapper crowdMapper ;

    @Override
    public int add(CrowdBaseDTO crowdBaseDTO) {
        crowdBaseDTO.setId(null);
        return this.crowdMapper.insert(dtoToPo(crowdBaseDTO));
    }

    @Override
    public int remove(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdMapper.deleteById(crowdBaseDTO.getId());
    }

    @Override
    public int edit(CrowdBaseDTO crowdBaseDTO) {
        return this.crowdMapper.updateById(dtoToPo(crowdBaseDTO));
    }

    @Override
    public List<CrowdBaseDTO> findByCrowdName(CrowdBaseDTO crowdBaseDTO,
                                              PageAndSizeDTO pageAndSizeDTO) {
        Page<CrowdPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize()) ;
        QueryWrapper<CrowdPO> wrapper = new QueryWrapper<>() ;
        wrapper.like("name",crowdBaseDTO.getName());
        return posToDtos(this.crowdMapper.selectPage(page, wrapper).getRecords());
    }

    @Override
    public CrowdBaseDTO findByCrowdId(CrowdBaseDTO crowdBaseDTO) {
        return poToDto(this.crowdMapper.selectById(crowdBaseDTO.getId()));
    }

    @Override
    public List<CrowdBaseDTO> findAllCrowd(PageAndSizeDTO pageAndSizeDTO) {
        Page<CrowdPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize());
        return posToDtos(this.crowdMapper.selectPage(page,null).getRecords());
    }

    @Override
    public List<CrowdBaseDTO> findByUserId(UserMessageDTO userMessageDTO,
                                           PageAndSizeDTO pageAndSizeDTO) {
        Page<CrowdPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize());
        QueryWrapper<CrowdPO> wrapper = new QueryWrapper<>() ;
        wrapper.eq("owner_id",userMessageDTO.getId()) ;
        return posToDtos(this.crowdMapper.selectPage(page, wrapper).getRecords());
    }

    private CrowdPO dtoToPo(CrowdBaseDTO crowdBaseDTO) {
        CrowdPO crowdPO = new CrowdPO() ;
        BeanUtils.copyProperties(crowdBaseDTO,crowdPO);
        return crowdPO ;
    }

    private CrowdBaseDTO poToDto(CrowdPO crowdPO) {
        CrowdBaseDTO crowdBaseDTO = new CrowdBaseDTO();
        BeanUtils.copyProperties(crowdPO, crowdBaseDTO);
        return crowdBaseDTO ;
    }

    private List<CrowdBaseDTO> posToDtos(List<CrowdPO> poList) {
        List<CrowdBaseDTO> DTOList = new ArrayList<>() ;
        for (CrowdPO crowdPO : poList) {
            CrowdBaseDTO crowdBaseDTO = new CrowdBaseDTO();
            BeanUtils.copyProperties(crowdPO, crowdBaseDTO);
            DTOList.add(crowdBaseDTO) ;
        }
        return DTOList;
    }
}
