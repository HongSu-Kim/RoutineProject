package com.soo.routine.controller.member;

import com.soo.routine.dto.member.*;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import com.soo.routine.security.LoginUser;
import com.soo.routine.service.member.MemberService;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    /*
    User Page
    */

    // 시작 페이지
    @GetMapping(value = {"/", "startRoutine"})
    public String startRoutine(){
        return "mypage/startRoutine";
    }

    // 회원가입 페이지
    @GetMapping("join")
    public String join(MemberJoinDTO memberJoinDTO){
        return "mypage/member/join";
    }

    // 회원가입
    @PostMapping("join")
    public String join(@Valid @ModelAttribute() MemberJoinDTO memberJoinDTO, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){ // 검증 실패시
            model.addAttribute("memberJoinDTO", memberJoinDTO); // 입력 데이터 값을 유지
            return "mypage/member/join";
        }

        memberService.join(memberJoinDTO);
        return "redirect:/login";
    }

    // 로그인 페이지
    @GetMapping("login")
    public String login(MemberDTO memberDTO) {
        return "mypage/member/login";
    }

    // 로그아웃
    @GetMapping("logout")
    public String logout() {
        return "redirect:/login";
    }

    // 비밀번호 찾기 페이지
    @GetMapping("pwd-find")
    public String pwdFind(@ModelAttribute("memberJoinDTO") MemberJoinDTO memberJoinDTO){
        return "mypage/member/pwd_find";
    }

    // 비밀번호 찾기
    @PostMapping("pwd-find")
    public String pwdFind(MemberDTO memberDTO, BindingResult bindingResult, Model model) throws Exception {

        if(bindingResult.hasErrors()){
            model.addAttribute("memberDTO", memberDTO);
            return "mypage/member/pwd_find";
        }

        Member member = memberService.pwdFind(memberDTO.getEmail());

        if(member == null) {
            bindingResult.addError(new FieldError("memberDTO", "email", "이메일이 올바르지 않습니다."));
            return "mypage/member/pwd_find";
        }

        return "redirect:/login";
    }

    // 마이페이지
    @GetMapping("mypage")
    public String mypage(@LoginUser SessionDTO sessionDTO, Model model) {

        model.addAttribute("sessionDTO", sessionDTO);
        return "mypage/member/mypage";
    }

    // 회원정보 수정 페이지
    @GetMapping("mypage-edit")
    public String editProfile(@LoginUser SessionDTO sessionDTO, MemberEditDTO memberEditDTO, Model model){

        model.addAttribute("sessionDTO", sessionDTO);
        return "mypage/member/edit_profile";
    }

    // 회원정보 수정
    @PostMapping("mypage-edit")
    public String editProfile(@Valid MemberEditDTO memberEditDTO, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("memberEditDTO", memberEditDTO);
            return "mypage/member/edit_profile";
        }

        memberService.edit(memberEditDTO);
        return "mypage/member/edit_profile";
    }

    // 회원탈퇴 페이지
    @GetMapping("mypage-withdraw")
    public String withdraw(MemberDTO memberDTO){
        return "mypage/member/withdraw";
    }

    // 회원탈퇴
    @PostMapping("mypage-withdraw")
    public String withdraw(@LoginUser SessionDTO sessionDTO, MemberDTO memberDTO,
                           BindingResult bindingResult, Model model){

        Member checkPwd = memberService.checkPwd(sessionDTO.getEmail(), memberDTO.getPwd());

        if (checkPwd == null) { // 비밀번호 불일치 시

            bindingResult.addError(new FieldError("memberDTO", "pwd", "비밀번호가 일치하지 않습니다."));
            return "mypage/member/withdraw";

        }else { // 비밀번호 일치 시

            memberService.withdraw(sessionDTO.getEmail());
            return "redirect:/login";
        }
    }

    // 접근 불가 페이지
    @GetMapping("deny")
    public String deny() {
        return "mypage/deny";
    }


    /*
    Admin Page
    */

    @GetMapping("admin")
    public String admin() {
        return "redirect:/admin/user-list?role=MEMBER";
    }

    @GetMapping("admin/user-list")
    public String memberList(Model model, Role role) {

        List<MemberReadDTO> lists = memberService.getMemberList(role);

        model.addAttribute("lists", lists);
        model.addAttribute("pageName", role.name() + " List");
        return "admin/user/list";
    }

}
