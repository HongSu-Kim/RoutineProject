package com.soo.routine.dto.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberWithdrawDTO {

    @NotBlank(message="비밀번호를 입력하세요.")
    private String pwd; // 비밀번호

}
