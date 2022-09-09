package com.soo.routine.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer replyNum;//답글번호 PK

    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;//회원번호 FK

    @ManyToOne
    @JoinColumn(name = "board_num")
    private Board board;//게시글번호 FK

    private String replyContent;//답글내용
    private LocalDateTime replyCreated;//답글작성일

}
