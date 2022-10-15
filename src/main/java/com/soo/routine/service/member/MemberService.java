package com.soo.routine.service.member;

import com.soo.routine.dto.member.MemberEditDTO;
import com.soo.routine.dto.member.MemberJoinDTO;
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
import org.springframework.validation.FieldError;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    /*
    Member Page
     */

    // 회원가입
    public void join(MemberJoinDTO memberJoinDTO) {

        // 이메일 중복 처리
        // 패스워드 불일치 처리
        // 닉네임 중복 처리

        Member member = new Member(Role.MEMBER, LocalDateTime.now(), true,
                memberJoinDTO.getEmail(), passwordEncoder.encode(memberJoinDTO.getPwd()),
                memberJoinDTO.getNickname(), memberJoinDTO.getGender(), memberJoinDTO.getBirth());

        memberRepository.save(member);
    }

//    try {
//        memberService.validateDuplicateMemberEmail(memberJoinDTO);
//    } catch (IllegalStateException e) {
//        bindingResult.addError(new FieldError("memberJoinDTO", "email", "이미 존재하는 이메일입니다."));
//    }
//
//    public Member join(MemberJoinDTO memberJoinDTO) throws Exception {
//        if(this.isEmailExist(memberJoinDTO.getEmail())) {
//            throw new Exception("이미 존재하는 이메일입니다.");
//        }
//        Member member = memberJoinDTO.toEntity();
//        member.hashPwd(passwordEncoder); // 비밀번호 암호화 후
//        return memberRepository.save(member); // 회원 등록
//    }
//\
//    private boolean isEmailExist(String email) {
//        Optional<Member> byEmail = memberRepository.findByEmail(email);
//        return !byEmail.isEmpty();
//    }
//
//    public void validateDuplicateMemberEmail(MemberJoinDTO memberJoinDTO) {
//        if (memberRepository.findByEmail(memberJoinDTO.getEmail()).isPresent()) {
//            throw new IllegalStateException();
//        }
//    }

    //패스워드 일치 여부 체크
//        if(!memberJoinDTO.getPwd().equals(memberJoinDTO.getPwd2())){
//            bindingResult.addError(new FieldError("memberJoinDTO", "pwd2", "패스워드가 일치하지 않습니다."));
//        }

    //닉네임 중복 체크
//        try {
//            memberService.validateDuplicateMemberNickname(memberJoinDTO);
//        } catch (IllegalStateException e) {
//            bindingResult.addError(new FieldError("memberJoinDTO", "nickname", "중복된 닉네임입니다"));
//        }

//        memberService.join(memberJoinDTO);

    // 닉네임 중복 체크
//    public void validateDuplicateMemberNickname(MemberJoinDTO memberJoinDTO) {
//        if (memberRepository.findByNickname(memberJoinDTO.getNickname()).isPresent()) {
//            throw new IllegalStateException();
//        }
//    }

    // 로그인

    // 이메일 존재 여부 체크
//    public Member checkEmail(String email) {
//        return memberRepository.findByEmail(email) // email로 회원을 조회해서
//                .filter(m -> m.getEmail().equals(email)) // 입력한 email과 같으면, 회원을 반환하고
//                .orElse(null); // 다르면 null을 반환한다
//    }

    //        Member join_checkEmail = memberService.checkEmail(memberJoinDTO.getEmail());
//
//        if (join_checkEmail != null) {
//            bindingResult.addError(new FieldError("memberJoinDTO", "email", "사용 불가능한 이메일입니다.")); // 오류 생성하고
//            return "mypage/member/join";
//        }

    // 이메일과 비밀번호 일치 여부 체크
    public Member checkPwd(String email, String pwd) {
        return memberRepository.findByEmail(email) // email로 회원을 조회하고
                .filter(m -> this.passwordEncoder.matches(pwd, m.getPwd())) // 암호화된 pwd(getPwd)와 입력한 pwd가 같으면, 회원을 반환하고
                .orElse(null); // 다르면 null을 반환한다
    }

    // 비밀번호 찾기
    public Member pwdFind(String email) throws Exception {

        // 회원정보 불러오기
        Member member = memberRepository.findByEmail(email).orElse(null);

        // 이메일 전송
        if(member!=null) {

            // 임시 비밀번호 생성
            String tempPwd = UUID.randomUUID().toString().replace("-", ""); // - 제거
            tempPwd = tempPwd.substring(0, 10); // 10자리로 생성

            System.out.print("임시 비밀번호 : " + tempPwd);
            member.findPwd(tempPwd);

            // 이메일 전송
//            MailUtil mail = new MailUtil();
//            mail.sendMail(member);

            // 암호화된 임시 비밀번호 저장
            member.findPwd(passwordEncoder.encode(member.getPwd()));

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
        member.active(false);
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
