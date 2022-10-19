package com.soo.routine.service.member;

import com.soo.routine.dto.member.MemberEditDTO;
import com.soo.routine.dto.member.MemberJoinDTO;
import com.soo.routine.dto.member.MemberReadDTO;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import com.soo.routine.repository.member.MemberRepository;
import com.soo.routine.util.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /*
    Member Page
     */

    // 회원가입
    public void join(MemberJoinDTO memberJoinDTO) {

        // 이메일 중복 처리
        // 패스워드 불일치 처리
        // 닉네임 중복 처리

        Member member = new Member(Role.MEMBER, LocalDateTime.now(),
                memberJoinDTO.getEmail(), passwordEncoder.encode(memberJoinDTO.getPwd()),
                memberJoinDTO.getNickname(), memberJoinDTO.getGender(), LocalDate.parse(memberJoinDTO.getBirth()));

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
    
    // 이메일과 비밀번호 일치 여부 체크
    // 회원탈퇴
    public Member checkPwd(String email, String pwd) {
        return memberRepository.findByEmail(email) // email로 회원을 조회하고
                .filter(m -> this.passwordEncoder.matches(pwd, m.getPwd())) // 입력한 pwd와 암호화된 pwd(m.getPwd)가 같으면, 회원을 반환하고
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
            MailUtil mail = new MailUtil();
            mail.sendMail(member);

            // 암호화된 임시 비밀번호 저장
            member.findPwd(passwordEncoder.encode(member.getPwd()));

            memberRepository.save(member);
        }
            return member;

    }
    
    // 회원정보 수정 페이지
    @Transactional
    public MemberEditDTO editPage(String email) {

        return memberRepository.findByEmail(email)
                .map(MemberEditDTO::new)
                .orElse(null);
    }

    // 회원정보 수정
    @Transactional
    public void edit(MemberEditDTO memberEditDTO) throws Exception {

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = memberRepository.findByEmail(memberEditDTO.getEmail()).orElse(null);
        System.out.print(member.getId());
        member.edit(passwordEncoder.encode(memberEditDTO.getPwd()),memberEditDTO.getNickname());

    }

    // 회원 탈퇴
    @Transactional
    public void withdraw(String email) {

        Member member = memberRepository.findByEmail(email).orElse(null);
        member.withdraw(Role.valueOf("WITHDRAW")); // 회원 비활성화
        memberRepository.save(member);
    }

    /*
    Admin Page
    */
    public Page<MemberReadDTO> getMemberList(Role role, Pageable pageable) {

		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by(sorts));

        return memberRepository.findByRole(role, pageable).map(MemberReadDTO::new);
    }
}
