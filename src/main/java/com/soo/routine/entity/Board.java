package com.soo.routine.entity;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@DynamicInsert
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardNum;//게시글번호 PK

    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;//회원번호 FK

    @Enumerated(EnumType.STRING)
    private BoardCategory category;//카테고리

    private String boardTitle;//게시글제목
    private String boardContent;//게시글내용
    private LocalDateTime boardCreate;//게시글작성일
    private LocalDateTime boardModify;//게시글수정일

    @ColumnDefault("0")
    private int boardHits;//게시글조회수


    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardImage> boardImageList;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

}
