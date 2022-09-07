package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    private int memberNum;//회원번호
    private String email;//이메일 주소
    private String pwd;//비밀번호
    private String nickname;//닉네임
    private String gender;//성별
    private String birth;//생년월일
    private String grade;//회원등급
    private String joinDate;//가입일

}
