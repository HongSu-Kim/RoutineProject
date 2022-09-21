package com.soo.routine.entity;

import com.soo.routine.dto.reply.ReplyWriteDTO;
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
    private LocalDateTime replyCreated;//답글작성일

    public Reply write(ReplyWriteDTO replyWriteDTO, Member member, Board board) {
        this.member = member;
        this.board = board;
        this.replyContent = replyWriteDTO.getReplyContent();
        this.replyCreated = LocalDateTime.now();
        return this;
    }

}
