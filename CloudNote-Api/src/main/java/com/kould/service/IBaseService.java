package com.kould.service;

import com.kould.dto.BlogBaseDTO;
import com.kould.dto.PageAndSizeDTO;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface IBaseService<T> {
    int add(T t) ;
    int remove(T t) ;
    int edit(T t) ;
    List<T> findByTTitle(T t, PageAndSizeDTO pageAndSizeDTO) ;
    T findByTId(T t) ;
    List<T> allTList(PageAndSizeDTO pageAndSizeDTO) ;
}
