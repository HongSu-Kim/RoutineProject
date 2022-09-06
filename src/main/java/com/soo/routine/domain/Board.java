package com.soo.routine.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    private int boardNum;
    private int idx;
    private String categoryName;
    private String boardTitle;
    private String boardContent;
    private String boardCreate;
    private String boardModify;
    private int boardHits;

}
