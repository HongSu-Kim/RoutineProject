package com.soo.routine.dto.member;

import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

// 인증된 유저 정보를 세션에 저장하기 위한 DTO

@Getter
public class SessionDTO implements Serializable {

    private Long memberId; // 회원번호
    private Role role; // 회원등급
    private String email; // 이메일 주소
    private String nickname; // 닉네임
    private String gender; // 성별
    private LocalDate birth; // 생년월일

    public SessionDTO(Member member) {
        this.memberId = member.getId();
        this.role = member.getRole();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.gender = member.getGender();
        this.birth = member.getBirth();
    }
    
}
