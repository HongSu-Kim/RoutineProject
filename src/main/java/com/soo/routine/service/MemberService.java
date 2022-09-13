package com.soo.routine.service;

import com.soo.routine.dto.MemberJoinDTO;
import com.soo.routine.entity.Member;
import com.soo.routine.mapper.MemberMapper;
import com.soo.routine.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Where;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    public Member join(MemberJoinDTO memberJoinDTO){

        Member member = new Member();
        member.join(memberJoinDTO, member);

//        Member member = modelMapper.map(memberJoinDTO, Member.class);

        memberRepository.save(member);

        return member;
    }

}
