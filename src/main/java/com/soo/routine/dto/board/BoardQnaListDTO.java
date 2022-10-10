package com.soo.routine.dto.board;

import com.soo.routine.entity.board.Reply;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class BoardQnaListDTO {

    private Long boardId;

    private Long memberId;
    private String memberEmail;

    private String category;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreate;
    private LocalDateTime boardModify;

    private Reply reply;

}
