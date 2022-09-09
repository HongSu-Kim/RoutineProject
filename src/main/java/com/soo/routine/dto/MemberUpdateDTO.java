package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberUpdateDTO {//회원정보 수정 시 사용

    private int memberNum;//회원번호
    private String grade;//회원등급
    private String joinDate;//가입일

    private String email;//이메일 주소

    @NotBlank(message="기존 비밀번호를 입력하세요.")
//  기존 비밀번호와 일치 여부 확인
    private String pwd;//기존 비밀번호

    @NotBlank(message="새로운 비밀번호를 입력하세요.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,16}",
            message = "8~16자 영문 대·소문자, 숫자를 사용하세요.")
//  기존 비밀번호와 불일치 여부 확인
    private String pwd_new;//비밀번호

    @NotBlank(message="비밀번호가 일치하지 않습니다.")
//  새 비밀번호와 일치 여부 확인
    private String pwd2;//비밀번호 확인

    @NotBlank(message="닉네임을 입력하세요.")
    private String nickname;//닉네임

    private String gender;//성별
    private String birth;//생년월일

}
