package com.soo.routine.service;

import com.soo.routine.dto.MemberJoinDTO;
import com.soo.routine.entity.Member;
import com.soo.routine.mapper.MemberMapper;
import com.soo.routine.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Where;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

//    @Transactional
    public Member join(MemberJoinDTO memberJoinDTO){

        Member member = new Member();
//        validateDuplicateMember(member);
        member.join(memberJoinDTO, member);

//        Member member = modelMapper.map(memberJoinDTO, Member.class);

        memberRepository.save(member);

        return member;
    }

    @Transactional(readOnly = false)
    public Member save(Member member) {
        validateDuplicateMemberNickname(member);
        validateDuplicateMemberEmail(member);
        return memberRepository.save(member);
    }

//    private void validateDuplicateMember(Member member) {
//        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
//        optionalMember.ifPresent(findUser -> {
//            throw new IllegalStateException("이미 사용 중인 이메일입니다.");
//        });
//    }

//    @Transactional
//    public boolean existsByEmail(String email){
//        return memberRepository.existsByEmail(email);
//    }

    private void validateDuplicateMemberNickname(Member member) {
        if (memberRepository.findByNickname(member.getNickname()).isPresent()) {
            throw new IllegalStateException("중복된 닉네임입니다.");
        }
    }

    private void validateDuplicateMemberEmail(Member member) {
        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            throw new IllegalStateException("중복된 이메일입니다.");
        }
    }

}
