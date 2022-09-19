package com.soo.routine.dto.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MemberFindPwdDTO {//Id·Password찾기 시 사용

    @NotBlank(message="이메일 주소를 입력하세요.")
    @Email(message = "이메일 형식에 맞게 입력하세요.")
    private String email;//이메일 주소

}
