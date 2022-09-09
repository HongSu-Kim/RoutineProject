package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberLoginDTO {//로그인 시 사용

    @NotBlank(message="이메일 주소를 입력하세요.")
    @Email(message = "이메일 형식에 맞게 입력하세요.")
//  이메일 일치 여부
    private String email;//이메일 주소

    @NotBlank(message="비밀번호를 입력하세요.")
//  비밀번호 일치 여부
    private String pwd;//비밀번호

}
