package com.soo.routine.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyUpdateDTO {

    private Long replyId;
    private Long memberId;
    private Long boardId;
    private String replyContent;
    private String replyCreate;

}
