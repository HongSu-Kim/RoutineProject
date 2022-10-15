package com.soo.routine.entity.member;

import com.soo.routine.entity.routine.Routine;
import com.soo.routine.entity.board.Board;
import com.soo.routine.entity.board.Reply;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; // 회원번호 PK

    @Enumerated(EnumType.STRING)
    private Role role; // 회원등급

    private LocalDateTime joinDate; // 가입일
    private boolean member_active; // 회원 활성화 유무

    @Column(unique = true)
    private String email; // 이메일 주소

    private String pwd; // 비밀번호

    @Column(unique = true)
    private String nickname; // 닉네임

    private String gender; // 성별
    private LocalDate birth; // 생년월일

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Routine> routineList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    // 회원가입
    @Builder
    public Member(Role role, LocalDateTime joinDate, boolean member_active,
                  String email, String pwd, String nickname, String gender, String birth){

        this.role = role;
        this.joinDate = joinDate;
        this.member_active = member_active;
        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
        this.gender = gender;
        this.birth = LocalDate.parse(birth);
    }

    // 회원정보 수정
    public void edit(String pwd, String nickname) {
        this.pwd = pwd;
        this.nickname = nickname;
    }

    // 비밀번호 찾기
    public void findPwd(String pwd) {
        this.pwd = pwd;
    }

    // 회원 활성화
    public void active(boolean member_active) {
        this.member_active = member_active;
    }

}
