package com.soo.routine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberNum;//회원번호 PK
    private String email;//이메일 주소
    private String pwd;//비밀번호
    private String nickname;//닉네임
    private String gender;//성별
    private LocalDate birth;//생년월일
    private String role;//회원등급
    private LocalDateTime joinDate;//가입일


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Board> boardList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Routine> routineList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

}
