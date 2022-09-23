package com.soo.routine.entity;

import com.soo.routine.dto.member.MemberJoinDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;//회원번호 PK

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

    @Builder
    public Member(String level, LocalDateTime joinDate, String email,
                  String pwd, String nickname, String gender, LocalDate birth){

        this.level = level;
        this.joinDate = joinDate;
        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
        this.gender = gender;
        this.birth = birth;
    }

}
