package com.kould.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kould.dto.PageAndSizeDTO;
import com.kould.dto.UserLoginDTO;
import com.kould.dto.UserMessageDTO;
import com.kould.mapper.UserMapper;
import com.kould.po.UserPO;
import com.kould.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper ;

    @Override
    public int add(UserLoginDTO userLoginDTO, UserMessageDTO userMessageDTO) {
        userLoginDTO.setId(null);
        userMessageDTO.setId(null);
        return this.userMapper.insert(dtoToPo(userLoginDTO, userMessageDTO));
    }

    @Override
    public int remove(UserMessageDTO userMessageDTO) {
        return this.userMapper.deleteById(userMessageDTO.getId());
    }

    @Override
    public int editMessage(UserMessageDTO userMessageDTO) {
        return this.userMapper.updateById(dtoToMessagePo(userMessageDTO));
    }

    @Override
    public int editLogin(UserLoginDTO userLoginDTO) {
        return this.userMapper.updateById(dtoToLoginPo(userLoginDTO));
    }

    @Override
    public List<UserMessageDTO> findByUserName(UserMessageDTO userMessageDTO, PageAndSizeDTO pageAndSizeDTO) {
        Page<UserPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize()) ;
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>() ;
        wrapper.like("name",userMessageDTO.getName()) ;
        return PosToDtos(this.userMapper.selectPage(page, wrapper).getRecords());
    }

    @Override
    public UserMessageDTO findByUserId(UserMessageDTO userMessageDTO) {
        return PoToMessageDto(this.userMapper.selectById(userMessageDTO.getId()));
    }

    @Override
    public List<UserMessageDTO> allUserList(PageAndSizeDTO pageAndSizeDTO) {
        Page<UserPO> page = new Page<>(pageAndSizeDTO.getIndex(), pageAndSizeDTO.getStepSize()) ;
        return PosToDtos(this.userMapper.selectPage(page,null).getRecords());
    }

    private UserPO dtoToPo (UserLoginDTO userLoginDTO, UserMessageDTO userMessageDTO) {
        UserPO userPO = new UserPO() ;
        BeanUtils.copyProperties(userLoginDTO,userPO);
        BeanUtils.copyProperties(userMessageDTO,userPO);
        return userPO ;
    }

    private UserPO dtoToLoginPo (UserLoginDTO userLoginDTO) {
        UserPO userPO = new UserPO() ;
        BeanUtils.copyProperties(userLoginDTO,userPO);
        return userPO ;
    }

    private UserPO dtoToMessagePo (UserMessageDTO userMessageDTODTO) {
        UserPO userPO = new UserPO() ;
        BeanUtils.copyProperties(userMessageDTODTO,userPO);
        return userPO ;
    }

    private UserMessageDTO PoToMessageDto (UserPO userPO) {
        UserMessageDTO userMessageDTO = new UserMessageDTO();
        BeanUtils.copyProperties(userPO,userMessageDTO);
        return userMessageDTO ;
    }

    private List<UserMessageDTO> PosToDtos(List<UserPO> POList) {
        List<UserMessageDTO> DTOList = new ArrayList<>() ;
        for (UserPO userPO : POList) {
            UserMessageDTO userMessageDTO = new UserMessageDTO();
            BeanUtils.copyProperties(userPO,userMessageDTO);
            DTOList.add(userMessageDTO) ;
        }
        return DTOList;
    }
}
