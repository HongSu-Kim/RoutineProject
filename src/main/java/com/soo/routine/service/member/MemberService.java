package com.soo.routine.service.member;

import com.soo.routine.dto.member.MemberReadDTO;
import com.soo.routine.entity.member.Member;
import com.soo.routine.mapper.member.MemberMapper;
import com.soo.routine.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
//public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    /*
    Member Page
     */

    /*
    로그인
    */
    // 이메일 존재 여부 체크
    public Member checkEmail(String email) {
        return memberRepository.findByEmail(email) // email로 회원을 조회하고
                .filter(m -> m.getEmail().equals(email)) // 그 회원의 pwd(getPwd)와 입력한 pwd가 같으면, 회원을 반환하고
                .orElse(null); // 다르면 null을 반환한다
    }

    // 이메일과 비밀번호 일치 여부 체크
    public Member checkPwd(String email, String pwd) {
        return memberRepository.findByEmail(email) // email로 회원을 조회하고
                .filter(m -> this.passwordEncoder.matches(pwd, m.getPwd())) // 암호화된 pwd(getPwd)와 입력한 pwd가 같으면, 회원을 반환하고
                .orElse(null); // 다르면 null을 반환한다
    }

    /*
    회원가입
    */
    // 회원 등록
    public void join(Member member) {
        memberRepository.save(member);
    }

//    public Member join(MemberJoinDTO memberJoinDTO) throws Exception {
//        if(this.isEmailExist(memberJoinDTO.getEmail())) {
//            throw new Exception("이미 존재하는 이메일입니다.");
//        }
//        Member member = memberJoinDTO.toEntity();
//        member.hashPwd(passwordEncoder); // 비밀번호 암호화 후
//        return memberRepository.save(member); // 회원 등록
//    }

    // 이메일 중복 체크
//    private boolean isEmailExist(String email) {
//        Optional<Member> byEmail = memberRepository.findByEmail(email);
//        return !byEmail.isEmpty();
//    }
//    public void validateDuplicateMemberEmail(MemberJoinDTO memberJoinDTO) {
//        if (memberRepository.findByEmail(memberJoinDTO.getEmail()).isPresent()) {
//            throw new IllegalStateException();
//        }
//    }

    // 닉네임 중복 체크
//    public void validateDuplicateMemberNickname(MemberJoinDTO memberJoinDTO) {
//        if (memberRepository.findByNickname(memberJoinDTO.getNickname()).isPresent()) {
//            throw new IllegalStateException();
//        }
//    }

    /*
    Admin Page
    */
    public List<MemberReadDTO> getMemberList(String level) {

        Type type = new TypeToken<List<MemberReadDTO>>() {}.getType();

        return modelMapper.map(memberRepository.findByLevel(level), type);
    }
}
