package com.kould.dto.comment;

import com.kould.dto.PageAndSizeDTO;
import lombok.Data;

@Data
public class CommentPackageDTO {
    private CommentBaseDTO commentBaseDTO ;
    private PageAndSizeDTO pageAndSizeDTO ;
}
