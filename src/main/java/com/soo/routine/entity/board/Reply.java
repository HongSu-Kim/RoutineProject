package com.soo.routine.entity.board;

import com.soo.routine.dto.board.ReplyWriteDTO;
import com.soo.routine.entity.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;//답글번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;//게시글번호 FK

    private String replyContent;//답글내용
    private LocalDateTime replyCreate;//답글작성일

    public Reply(ReplyWriteDTO replyWriteDTO, Member member, Board board) {
        this.member = member;
        this.board = board;
        this.replyContent = replyWriteDTO.getReplyContent();
        this.replyCreate = LocalDateTime.now();
    }

}
