package com.soo.routine.dto;

import com.soo.routine.entity.BoardCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BoardReadDTO {

    private int boardNum;
    private int memberNum;
    private BoardCategory category;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreate;
    private LocalDateTime boardModify;
    private int boardHits;

    private List<String> imageFileName;

    private String nickname;

}
