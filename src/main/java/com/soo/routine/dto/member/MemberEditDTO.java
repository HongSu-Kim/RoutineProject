package com.soo.routine.dto.member;

import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class MemberEditDTO {

    private Long memberId; // 회원번호
    private Role role; // 회원등급
    private LocalDateTime joinDate; // 가입일
    private String email; // 이메일 주소

    @NotBlank(message="기존 비밀번호를 입력하세요.")
    private String pwd; // 기존 비밀번호

    @NotBlank(message="새로운 비밀번호를 입력하세요.")
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])(?=\\S+$).{5,15}",
            message = "5~15자 이내의 영문 및 숫자를 사용하세요.")
    private String newPwd; // 비밀번호

    @NotBlank(message="새로운 비밀번호를 입력하세요.")
    private String newPwd2; // 비밀번호 확인

    @NotBlank(message="닉네임을 입력하세요.")
    private String nickname; // 닉네임

    private String gender; // 성별
    private String birth; // 생년월일

//     Entity → DTO
//    public MemberEditDTO(Member member) {
//        System.out.print(member.getId());
//        this.id = member.getId();
//        this.email = member.getEmail();
//        this.nickname = member.getNickname();
//        this.gender = member.getGender();
//        this.birth = String.valueOf(member.getBirth());
//    }

    public Member toEntity(){
        return Member
                .builder()
                .email(email)//
                .pwd(pwd)
                .nickname(nickname)
                .gender(gender)/////
                .birth(birth)/////
                .build();
    }

}
