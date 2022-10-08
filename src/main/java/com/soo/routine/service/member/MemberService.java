package com.soo.routine.service.member;

import com.soo.routine.dto.board.BoardReadDTO;
import com.soo.routine.dto.member.MemberEditDTO;
import com.soo.routine.dto.member.MemberReadDTO;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import com.soo.routine.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
//public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
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
        return memberRepository.findByEmail(email) // email로 회원을 조회해서
                .filter(m -> m.getEmail().equals(email)) // 입력한 email과 같으면, 회원을 반환하고
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
    회원 탈퇴
     */
    @Transactional
    public void change_memberActive(String email) {

//        Member change_memberActive = memberRepository.findOne(email);
//
//        change_memberActive.setMember_active(member.get);
//
//        Optional<Member> member = memberRepository.findByEmail(email);
//        member.change_memberActive(member_active);


        // 회원 활성화
        Member member = memberRepository.findByEmail(email).orElse(null);
        member.setMember_active(false);
        memberRepository.save(member);
    }

    /*
    비밀번호 찾기
    */
    public Member pwdFind(String email) throws Exception {

        // 회원정보 불러오기
        Member member = memberRepository.findByEmail(email).orElse(null);

        // 이메일 전송
        if(member!=null) {

            // 임시 비밀번호 생성
            String tempPwd = UUID.randomUUID().toString().replace("-", ""); // - 제거
            tempPwd = tempPwd.substring(0, 10); // 10자리로 생성

            System.out.print("임시 비밀번호 : " + tempPwd);
            member.setPwd(tempPwd);

            // 이메일 전송
//            MailUtil mail = new MailUtil();
//            mail.sendMail(member);

            // 암호화된 임시 비밀번호 저장
            member.setPwd(passwordEncoder.encode(member.getPwd()));

            memberRepository.save(member);
        }
            return member;

    }
    
    /*
    회원정보 수정
    */
    @Transactional
    public void edit(MemberEditDTO memberEditDTO) {

        Member member = memberRepository.findByEmail(memberEditDTO.getEmail()).orElse(null);

        if (member == null) {
            return;
        }

//        member.setPwd(passwordEncoder.encode(memberEditDTO.getPwd()));
//        member.setNickname(memberEditDTO.getNickname());

        member.edit(passwordEncoder.encode(memberEditDTO.getNewPwd()), memberEditDTO.getNickname());

        memberRepository.save(member);
    }

    /*
    Admin Page
    */
    public List<MemberReadDTO> getMemberList(Role role) {

        Type type = new TypeToken<List<MemberReadDTO>>() {}.getType();

        return modelMapper.map(memberRepository.findByrole(role), type);
    }
}
