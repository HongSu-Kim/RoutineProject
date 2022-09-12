package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BoardUpdateDTO {

    private int boardId;
    private int memberid;
    private String category;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreate;
    private LocalDateTime boardModify;
    private int boardHits;

    private List<String> imageFileName;

    private String nickname;

}
