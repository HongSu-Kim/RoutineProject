package com.soo.routine.dto.member;

import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberReadDTO {

    private Long memberId; // 회원번호
	private Role role; // 회원 등급
	private LocalDateTime joinDate; // 가입일
    private String email; // 이메일 주소
    private String nickname; // 닉네임
    private String gender; // 성별
    private LocalDate birth; // 생년월일

	public MemberReadDTO(Member member) { // Entity → DTO
		memberId = member.getId();
		role = member.getRole();
		joinDate = member.getJoinDate();
		email = member.getEmail();
		nickname = member.getNickname();
		gender = member.getGender();
		birth = member.getBirth();
	}

}
