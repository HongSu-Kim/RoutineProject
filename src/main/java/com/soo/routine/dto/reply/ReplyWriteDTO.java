package com.soo.routine.dto.reply;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ReplyWriteDTO {

    private int boardId;
    
    @NotBlank(message = "내용을 입력해 주세요.")
    private String replyContent;

}
