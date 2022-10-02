package com.soo.routine.controller.member;

import com.soo.routine.dto.member.MemberResetPwdDTO;
import com.soo.routine.dto.member.MemberJoinDTO;
import com.soo.routine.dto.member.MemberLoginDTO;
import com.soo.routine.dto.member.MemberReadDTO;
import com.soo.routine.entity.member.Role;
import com.soo.routine.entity.member.Member;
import com.soo.routine.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    /*
    Start Page
    */
    @GetMapping("startRoutine")
    public String getStartRoutine(){
        return "mypage/startRoutine";
    }

    /*
    User Page
    */
    @GetMapping("mypage")
    public String mypage(@AuthenticationPrincipal @SessionAttribute(name = "loginMember", required = false)Member loginMember, Model model) {

//        Member loginMember = httpSession.getAttribute("loginMember");

        model.addAttribute("member", loginMember);

        return "mypage/member_mypage";
    }

    @GetMapping("login")
    public String login(@SessionAttribute(name = "loginMember", required = false)Member loginMember,
                        Model model, MemberLoginDTO memberLoginDTO) {

        if (loginMember == null) {
            return "mypage/member_login";
        }

        model.addAttribute("member", loginMember);

        return "redirect:/mypage";
    }

    @PostMapping("login")
    public String login(@Valid @ModelAttribute() MemberLoginDTO memberLoginDTO,
                        BindingResult bindingResult, Model model){

        // 오류 발생 처리
        if(bindingResult.hasErrors()){
            model.addAttribute("memberLoginDTO", memberLoginDTO);
            return "mypage/member_login";
        }

        Member login_checkEmail = memberService.checkEmail(memberLoginDTO.getEmail()); // service를 호출해서
        Member login_checkPwd = memberService.checkPwd(memberLoginDTO.getEmail(), memberLoginDTO.getPwd()); // service를 호출해서

        //로그인 실패 처리
        if (login_checkEmail == null) {
            bindingResult.addError(new FieldError("memberLoginDTO", "email", "이메일이 존재하지 않습니다.")); // 오류 생성하고
            return "mypage/member_login"; // 다시 로그인 페이지로 이동
        }
        if (login_checkPwd == null) {
            bindingResult.addError(new FieldError("memberLoginDTO", "pwd", "비밀번호가 일치하지 않습니다.")); // 오류 생성하고
            return "mypage/member_login"; // 다시 로그인 페이지로 이동
        }
        if (!login_checkEmail.isMember_active()) {
            bindingResult.addError(new FieldError("memberLoginDTO", "email", "사용 불가능한 이메일입니다.")); // 오류 생성하고
            return "mypage/member_login";
        }

        // 로그인 성공 처리
//        HttpSession session = request.getSession(); // 세션이 있으면 세션 반환, 없으면 세션을 생성해서 반환
        httpSession.setAttribute("loginMember", login_checkPwd); // 세션에 로그인 회원 정보 보관

        return "redirect:/mypage"; // 로그인 성공 시 마이페이지로 이동
    }

    @GetMapping("logout")
    public String logout() {

        if (httpSession != null) {
            httpSession.invalidate(); // 세션 제거
        }

        return "redirect:/login";
    }

    @GetMapping("resetPwd")
    public String resetPwd(@SessionAttribute(name = "loginMember", required = false)Member loginMember,
                          Model model, MemberResetPwdDTO memberResetPwdDTO){
        return "mypage/member_resetPwd";
    }
    @PostMapping("resetPwd")
    public String resetPwd(MemberResetPwdDTO memberResetPwdDTO, BindingResult bindingResult, Model model,
                           HttpServletRequest request, @SessionAttribute(name = "loginMember")Member loginMember){

        // 오류 발생 처리
        if(bindingResult.hasErrors()){
            model.addAttribute("memberResetPwdDTO", memberResetPwdDTO);
            return "mypage/member_resetPwd";
        }

        Member resetPwd_checkBirth = memberService.checkBirth(loginMember.getEmail(), loginMember.getBirth());

        //이메일 또는 생년월일 불일치 처리
        if (resetPwd_checkBirth == null) {
            bindingResult.addError(new FieldError("memberLoginDTO", "birth", "이메일 또는 생년월일이 일치하지 않습니다."));
            return "mypage/member_resetPwd";
        }

        return "mypage/member_resetPwd";
    }

    @GetMapping("join")
    public String join(MemberJoinDTO memberJoinDTO){
        return "mypage/member_join";
    }
    @PostMapping("join")
    public String join(@Valid @ModelAttribute() MemberJoinDTO memberJoinDTO, BindingResult bindingResult, Model model){

        //검증 실패시
        if(bindingResult.hasErrors()){
            model.addAttribute("memberJoinDTO", memberJoinDTO);
            return "mypage/member_join";
        }

        Member join_checkEmail = memberService.checkEmail(memberJoinDTO.getEmail());

        if (join_checkEmail != null) {
            bindingResult.addError(new FieldError("memberJoinDTO", "email", "사용 불가능한 이메일입니다.")); // 오류 생성하고
            return "mypage/member_join";
        }

        ModelMapper modelMapper = new ModelMapper();
//        Member member = modelMapper.map(memberJoinDTO, Member.class);
        Member member = new Member(memberJoinDTO.getEmail(), memberJoinDTO.getPwd(),
                memberJoinDTO.getNickname(), memberJoinDTO.getGender(),
                memberJoinDTO.getBirth(), Role.MEMBER, LocalDateTime.now(), true);

        member.setPwd(passwordEncoder.encode(member.getPwd()));
        memberService.join(member);

        //패스워드 일치 여부 체크
//        if(!memberJoinDTO.getPwd().equals(memberJoinDTO.getPwd2())){
//            bindingResult.addError(new FieldError("memberJoinDTO", "pwd2", "패스워드가 일치하지 않습니다."));
//        }

        //이메일 중복 체크
//        try {
//            memberService.validateDuplicateMemberEmail(memberJoinDTO);
//        } catch (IllegalStateException e) {
//            bindingResult.addError(new FieldError("memberJoinDTO", "email", "이미 존재하는 이메일입니다."));
//        }

        //닉네임 중복 체크
//        try {
//            memberService.validateDuplicateMemberNickname(memberJoinDTO);
//        } catch (IllegalStateException e) {
//            bindingResult.addError(new FieldError("memberJoinDTO", "nickname", "중복된 닉네임입니다"));
//        }

//        memberService.join(memberJoinDTO);

        return "redirect:/login";
    }

    @GetMapping("editProfile")
    public String getUpdate(){
        return "mypage/member_editProfile";
    }
    @PostMapping("editProfile")
    public String postUpdate(){
        return "mypage/member_editProfile";
    }

    @GetMapping("withdraw")
    public String withdraw(@SessionAttribute(name = "loginMember", required = false)Member loginMember,
                           Model model, MemberLoginDTO memberLoginDTO){
        return "mypage/member_withdraw";
    }
    @PostMapping("withdraw")
    public String withdraw(MemberLoginDTO memberLoginDTO, BindingResult bindingResult, Model model,
                           HttpServletRequest request, @SessionAttribute(name = "loginMember")Member loginMember){

        Member login_checkPwd = memberService.checkPwd(loginMember.getEmail(), memberLoginDTO.getPwd());

        if (login_checkPwd == null) {
            bindingResult.addError(new FieldError("memberLoginDTO", "pwd", "비밀번호가 일치하지 않습니다.")); // 오류 생성하고
            return "mypage/member_withdraw"; // 다시 회원탈퇴 페이지로 이동

        // 비밀번호 일치 시
        }else {

            memberService.change_memberActive(loginMember.getEmail());
//            loginMember.setMember_active(false); // 멤버 비활성화

            if (httpSession!=null) {
                httpSession.invalidate(); // 세션 제거
            }

            return "redirect:/login";
        }
    }


    /*
    Admin Page
    */
    @GetMapping("admin/user-list")
    public String memberList(Model model, Role role) {

        List<MemberReadDTO> lists = memberService.getMemberList(role);

        model.addAttribute("lists", lists);
//        model.addAttribute("role", role);
        model.addAttribute("pageName", role + " List");
        return "admin/user_list";
    }

}
