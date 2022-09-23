package com.soo.routine.service;

import com.soo.routine.dto.member.MemberJoinDTO;
import com.soo.routine.dto.member.MemberReadDTO;
import com.soo.routine.entity.Member;
import com.soo.routine.entity.Role;
import com.soo.routine.mapper.MemberMapper;
import com.soo.routine.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    /* 회원가입 */
    @Transactional
    public Long join(MemberJoinDTO memberJoinDTO) {

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberJoinDTO.setPwd(passwordEncoder.encode(memberJoinDTO.getPwd()));

        return memberRepository.save(memberJoinDTO.toEntity()).getId();
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Member> userEntityWrapper = memberRepository.findByEmail(email);
        Member userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPwd(), authorities);
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
