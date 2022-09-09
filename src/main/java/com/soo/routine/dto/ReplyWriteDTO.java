package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyWriteDTO {

    private int replyNum;
    private int memberNum;
    private int boardNum;
    private String replyContent;
    private String replyCreated;

}
