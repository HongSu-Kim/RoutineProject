package com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDTO {

    private int replyNum;
    private int idx;
    private int boardNum;
    private String replyContent;
    private String replyCreated;

}
