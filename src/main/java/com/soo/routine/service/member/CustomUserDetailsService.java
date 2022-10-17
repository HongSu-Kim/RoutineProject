package com.soo.routine.service.member;

import com.soo.routine.dto.member.SessionDTO;
import com.soo.routine.entity.member.Member;
import com.soo.routine.repository.member.MemberRepository;
import com.soo.routine.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다.")); // 이메일 불일치 처리

        httpSession.setAttribute("member", new SessionDTO(member)); // Security Session에 유저 정보를 저장한다
        return new CustomUserDetails(member);
    }
}
