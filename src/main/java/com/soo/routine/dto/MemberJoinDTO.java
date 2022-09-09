package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberJoinDTO {//회원가입 시 사용

    private int memberNum;//회원번호
    private String grade;//회원등급
    private String joinDate;//가입일

    @NotBlank(message="이메일 주소를 입력하세요.")
    @Email(message = "이메일 형식에 맞게 입력하세요.")
    private String email;//이메일 주소

    @NotBlank(message="비밀번호를 입력하세요.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,16}",
            message = "8~16자 영문 대·소문자, 숫자를 사용하세요.")
    private String pwd;//비밀번호

    @NotBlank(message="비밀번호가 일치하지 않습니다.")
//  비밀번호 일치 여부 확인
    private String pwd2;//비밀번호 확인

    @NotBlank(message="닉네임을 입력하세요.")
    private String nickname;//닉네임

    @NotBlank(message="성별을 입력하세요.")
    private String gender;//성별

    @NotBlank(message="생년월일을 입력하세요.")
    private String birth;//생년월일

}
