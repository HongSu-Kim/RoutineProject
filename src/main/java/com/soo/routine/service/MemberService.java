package com.soo.routine.service;

import com.soo.routine.dto.member.MemberJoinDTO;
import com.soo.routine.dto.member.MemberReadDTO;
import com.soo.routine.entity.Member;
import com.soo.routine.mapper.MemberMapper;
import com.soo.routine.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    /* 회원가입 */

    @Transactional
    public Member join(MemberJoinDTO memberJoinDTO) {

        Member member = new Member().join(memberJoinDTO);
        memberRepository. save(member);

        return member;
    }

    //이메일 중복 체크
    public void validateDuplicateMemberEmail(MemberJoinDTO memberJoinDTO) {
        if (memberRepository.findByEmail(memberJoinDTO.getEmail()).isPresent()) {
            throw new IllegalStateException();
        }
    }

    //닉네임 중복 체크
    public void validateDuplicateMemberNickname(MemberJoinDTO memberJoinDTO) {
        if (memberRepository.findByNickname(memberJoinDTO.getNickname()).isPresent()) {
            throw new IllegalStateException();
        }
    }

    /*
    Admin Page
    */
    public List<MemberReadDTO> getMemberList(String level) {

        Type type = new TypeToken<List<MemberReadDTO>>() {}.getType();

        return modelMapper.map(memberRepository.findByLevel(level), type);
    }
}
