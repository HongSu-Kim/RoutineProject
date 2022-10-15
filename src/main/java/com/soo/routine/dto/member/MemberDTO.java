package com.soo.routine.dto.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

// 로그인, 비밀번호 찾기, 회원탈퇴 시 사용

@Getter
@Setter
public class MemberDTO {

    @NotBlank(message="이메일 주소를 입력하세요.")
    @Email(message = "이메일 형식에 맞게 입력하세요.")
    private String email; // 이메일 주소

    @NotBlank(message="비밀번호를 입력하세요.")
    private String pwd; // 비밀번호

}
