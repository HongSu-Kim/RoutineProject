package com.soo.routine.dto.board;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class BoardWriteDTO {

    @NotNull
    private Long memberId;

    private String memberNickname;

    @NotBlank(message = "카테고리를 선택해주세요.")
    private String category;
    
    @NotBlank(message = "제목을 입력해주세요.")
    private String boardTitle;

    @NotBlank(message = "내용을 입력해주세요.")
    private String boardContent;

    private List<String> imageFileName;

}
