package com.soo.routine.entity.board;

import com.soo.routine.dto.board.BoardEditDTO;
import com.soo.routine.dto.board.BoardWriteDTO;
import com.soo.routine.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;//게시글번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    private String category;//카테고리
    private String boardTitle;//게시글제목
    private String boardContent;//게시글내용
    private LocalDateTime boardCreate;//게시글작성일
    private LocalDateTime boardModify;//게시글수정일

    private int boardHits;//게시글조회수


    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardImage> boardImageList;

    @OneToOne(mappedBy = "board", cascade = CascadeType.REMOVE)
    private Reply reply;

    // 글 작성
    public Board(BoardWriteDTO boardWriteDTO, Member member) {
        this.member = member;
        this.category = boardWriteDTO.getCategory();
        this.boardTitle = boardWriteDTO.getBoardTitle();
        this.boardContent = boardWriteDTO.getBoardContent();
        this.boardCreate = LocalDateTime.now();
        this.boardHits = 0;
    }

    // 글 수정
    public void edit(BoardEditDTO boardEditDTO) {
        this.boardTitle = boardEditDTO.getBoardTitle();
        this.boardContent = boardEditDTO.getBoardContent();
        this.boardModify = LocalDateTime.now();
    }


}
