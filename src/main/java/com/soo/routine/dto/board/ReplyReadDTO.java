package com.soo.routine.dto.board;

import com.soo.routine.entity.board.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReplyReadDTO {

    private Long replyId;
	private Long memberId;
	private String memberNickname;
	private String memberEmail;
    private Long boardId;
    private String replyContent;
    private LocalDateTime replyCreate;

	public ReplyReadDTO(Reply reply) {
		replyId = reply.getId();
		memberId = reply.getMember().getId();
		memberNickname = reply.getMember().getNickname();
		memberEmail = reply.getMember().getEmail();
		boardId = reply.getBoard().getId();
		replyContent = reply.getReplyContent();
		replyCreate = reply.getReplyCreate();
	}

}
