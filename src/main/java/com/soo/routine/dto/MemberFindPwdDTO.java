package com.soo.routine.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberFindPwdDTO {//Id·Password찾기 시 사용

    @NotBlank(message="이메일 주소를 입력하세요.")
    @Email(message = "이메일 형식에 맞게 입력하세요.")
    private String email;//이메일 주소

}
