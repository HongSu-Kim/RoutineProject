package com.soo.routine.entity;

import com.soo.routine.dto.member.MemberJoinDTO;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer id;//회원번호 PK

    @Column(unique = true)
    private String email;//이메일 주소

    private String pwd;//비밀번호

    @Column(unique = true)
    private String nickname;//닉네임

    private String gender;//성별
    private LocalDate birth;//생년월일

    private String level;//회원등급
    private LocalDateTime joinDate;//가입일


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Board> boardList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Routine> routineList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;


    public Member join(MemberJoinDTO memberJoinDTO){

        this.level = "member";
        this.joinDate = LocalDateTime.now();
        this.email = memberJoinDTO.getEmail();
        this.pwd = memberJoinDTO.getPwd();
        this.nickname = memberJoinDTO.getNickname();
        this.gender = memberJoinDTO.getGender();
        this.birth = LocalDate.parse(memberJoinDTO.getBirth());

        return this;
    }

}
