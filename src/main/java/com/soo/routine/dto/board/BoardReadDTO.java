package com.soo.routine.dto.board;

import com.soo.routine.entity.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BoardReadDTO {

    private int boardId;

    private int memberId;
    private String memberNickname;

    private String category;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreate;
    private LocalDateTime boardModify;
    private int boardHits;

    private List<String> imageFileNameList;

    private List<Reply> replyList;

}
