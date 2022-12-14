package com.soo.routine.entity.board;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_image_id")
    private Long id; // 이미지 번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; // 게시글 번호 FK

    private String imageFileName; // 이미지 파일 이름

}
