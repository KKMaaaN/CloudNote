package com.kould.dto.blog;

import com.kould.dto.PageAndSizeDTO;
import lombok.Data;

@Data
public class BlogPackageDTO {
    private BlogBaseDTO blogBaseDTO;
    private PageAndSizeDTO pageAndSizeDTO;
}
