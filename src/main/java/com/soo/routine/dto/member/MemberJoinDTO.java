package com.soo.routine.dto.member;

import com.soo.routine.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberJoinDTO {//회원가입 시 사용

    private long memberId;//회원번호
    private String level;//회원등급
    private LocalDateTime joinDate;//가입일

    @NotBlank(message="이메일 주소를 입력하세요.")
    @Email(message = "이메일 형식에 맞게 입력하세요.")
    private String email;//이메일 주소

    @NotBlank(message="비밀번호를 입력하세요.")
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])(?=\\S+$).{5,15}",
            message = "5~15자 이내의 영문 및 숫자를 사용하세요.")
    private String pwd;//비밀번호

    @NotBlank(message="비밀번호를 입력하세요.")
    private String pwd2;//비밀번호 확인

    @NotBlank(message="닉네임을 입력하세요.")
    private String nickname;//닉네임

    @NotBlank(message="성별을 입력하세요.")
    private String gender;//성별

    @NotBlank(message="생년월일을 입력하세요.")
    private String birth;//생년월일

    public Member toEntity(){
        return Member
                .builder()
                .email(email)
                .pwd(pwd)
                .nickname(nickname)
                .gender(gender)
                .birth(birth)
                .build();
    }

}
