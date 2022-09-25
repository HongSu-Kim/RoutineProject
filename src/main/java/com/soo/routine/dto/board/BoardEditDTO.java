package com.soo.routine.dto.board;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class BoardEditDTO {

    private Long boardId;
    private String memberNickname;
    private String category;

    @NotBlank(message = "제목을 입력해주세요.")
    private String boardTitle;

    @NotBlank(message = "내용을 입력해주세요.")
    private String boardContent;

    private List<String> imageFileName;

}
