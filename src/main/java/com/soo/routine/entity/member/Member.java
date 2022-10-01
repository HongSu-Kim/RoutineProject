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
import java.util.List;

@Entity
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id; // 회원번호 PK

    @Column(unique = true)
    private String email; // 이메일 주소

    private String pwd; // 비밀번호

    @Column(unique = true)
    private String nickname; // 닉네임

    private String gender; // 성별
    private LocalDate birth; // 생년월일

    @Enumerated(EnumType.STRING)
    private Level level; // 회원등급

    private LocalDateTime joinDate; // 가입일

    private boolean member_active; // 회원 활성화 유무

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Board> boardList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Routine> routineList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    @Builder
    public Member(String email, String pwd, String nickname, String gender,
                  String birth, Level level, LocalDateTime joinDate, boolean member_active){

        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
        this.gender = gender;
        this.birth = LocalDate.parse(birth, DateTimeFormatter.ISO_LOCAL_DATE);
        this.level = level;
        this.joinDate = joinDate;
        this.member_active = member_active;
    }

}
