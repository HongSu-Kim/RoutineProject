package com.soo.routine.dto.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PwdResetDTO {

    @NotBlank(message="이메일 주소를 입력하세요.")
    @Email(message = "이메일 형식에 맞게 입력하세요.")
    private String email; // 이메일 주소

    @NotBlank(message="생년월일을 입력하세요.")
    private String birth; // 생년월일

}
