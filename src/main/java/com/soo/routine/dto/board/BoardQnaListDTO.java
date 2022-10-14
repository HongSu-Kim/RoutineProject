package com.soo.routine.dto.board;

import com.soo.routine.entity.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardQnaListDTO {

    private Long boardId;

    private Long memberId;
    private String memberEmail;

    private String category;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime boardCreate;
    private LocalDateTime boardModify;

	private Long replyId;

	public BoardQnaListDTO(Board board) {
		boardId = board.getId();
		memberId = board.getMember().getId();
		memberEmail = board.getMember().getEmail();
		category = board.getCategory();
		boardTitle = board.getBoardTitle();
		boardContent = board.getBoardContent();
		boardCreate = board.getBoardCreate();
		boardModify = board.getBoardModify();

		if (board.getReply() != null){
			replyId = board.getReply().getId();
		}
	}

}
