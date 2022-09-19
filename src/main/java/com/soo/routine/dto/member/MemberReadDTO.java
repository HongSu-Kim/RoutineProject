package com.soo.routine.dto.member;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberReadDTO {

    private int memberId;//회원번호
    private String email;//이메일 주소
    private String nickname;//닉네임
    private String gender;//성별
    private LocalDate birth;//생년월일
    private String level;//회원등급
    private LocalDateTime joinDate;//가입일

}
