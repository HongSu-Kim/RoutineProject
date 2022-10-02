package com.soo.routine.entity.member;

import org.springframework.security.config.annotation.SecurityBuilder;

public class SessionMember implements SecurityBuilder<Member> {

	private Long memberId;
	private String email;
	private String pwd;
	private Role role;

	@Override
	public Member build() throws Exception {
		return null;
	}
}
