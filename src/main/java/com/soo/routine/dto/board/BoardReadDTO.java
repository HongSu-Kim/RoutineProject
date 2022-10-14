package com.soo.routine.dto.board;

import com.soo.routine.entity.board.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BoardReadDTO {

    private Long boardId;

    private Long memberId;
    private String memberNickname;
    private String memberEmail;

    private String category;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreate;
    private LocalDateTime boardModify;
    private int boardHits;

    private List<String> imageFileNameList;

    private Reply reply;

}
