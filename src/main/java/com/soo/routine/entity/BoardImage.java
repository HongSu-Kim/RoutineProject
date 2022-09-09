package com.soo.routine.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageNum; // 이미지 번호 PK

    @ManyToOne
    @JoinColumn(name = "board_num")
    private Board board; // 게시글 번호 FK

    private String imageFileName; // 이미지 파일 이름

}
