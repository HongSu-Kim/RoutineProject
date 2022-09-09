package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardWriteDTO {

    private int boardNum;
    private int memberNum;
    private String categoryName;
    private String boardTitle;
    private String boardContent;
    private String boardCreate;
    private String boardModify;
    private int boardHits;

    private List<String> imageFileName;

    private String nickname;

}
