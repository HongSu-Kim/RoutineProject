package com.soo.routine.dto.board;

import com.soo.routine.entity.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

	private ReplyReadDTO replyReadDTO;

	public BoardReadDTO(Board board) {
		boardId = board.getId();
		memberId = board.getMember().getId();
		memberNickname = board.getMember().getNickname();
		memberEmail = board.getMember().getEmail();
		category = board.getCategory();
		boardTitle = board.getBoardTitle();
		boardContent = board.getBoardContent();
		boardCreate = board.getBoardCreate();
		boardModify = board.getBoardModify();
		boardHits = board.getBoardHits();
		imageFileNameList = board.getBoardImageList().stream()
				.map(boardImage -> boardImage.getImageFileName())
				.collect(Collectors.toList());
		if (board.getReply() != null)
		replyReadDTO = new ReplyReadDTO(board.getReply());
	}

}
