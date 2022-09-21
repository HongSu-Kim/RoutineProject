package com.soo.routine.dto.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardListDTO {

    private Long boardId;
    private Long memberId;
    private String category;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreate;
    private LocalDateTime boardModify;
    private int boardHits;

}
