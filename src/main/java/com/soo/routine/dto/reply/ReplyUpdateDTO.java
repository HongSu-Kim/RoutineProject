package com.soo.routine.dto.reply;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyUpdateDTO {

    private int replyId;
    private int memberId;
    private int boardId;
    private String replyContent;
    private String replyCreated;

}
