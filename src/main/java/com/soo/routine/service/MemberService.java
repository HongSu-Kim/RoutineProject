package com.soo.routine.service;

import com.soo.routine.dto.MemberJoinDTO;
import com.soo.routine.entity.Member;
import com.soo.routine.mapper.MemberMapper;
import com.soo.routine.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    public Member join(String email, String pwd,
                       String nickname, String gender, LocalDate birth){

        Member member = new Member();

        member.setEmail(email);
        member.setPwd(pwd);
        member.setNickname(nickname);
        member.setGender(gender);
        member.setBirth(birth);

        memberRepository.save(member);

        return member;
    }

}
