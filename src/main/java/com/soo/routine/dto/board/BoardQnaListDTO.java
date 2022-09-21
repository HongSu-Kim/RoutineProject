package com.soo.routine.dto.board;

import com.soo.routine.entity.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardQnaListDTO {

    private Long boardId;

    private Long memberId;
    private String memberEmail;

    private String category;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreate;
    private LocalDateTime boardModify;
    private int boardHits;

    private Reply reply;

}
