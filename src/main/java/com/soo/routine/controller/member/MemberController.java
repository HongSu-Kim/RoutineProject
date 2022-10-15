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
    private final HttpSession httpSession;

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
    public String pwdFind(MemberJoinDTO memberJoinDTO, BindingResult bindingResult, Model model) throws Exception {

        if(bindingResult.hasErrors()){
            model.addAttribute("memberJoinDTO", memberJoinDTO);
            return "mypage/member/pwd_find";
        }

        Member member = memberService.pwdFind(memberJoinDTO.getEmail());

        if(member == null) {
            bindingResult.addError(new FieldError("memberJoinDTO", "email", "이메일이 올바르지 않습니다."));
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
    @GetMapping("edit-profile")
    public String profileEdit(MemberEditDTO memberEditDTO, Model model){

        model.addAttribute("memberEditDTO", new MemberEditDTO());
        return "mypage/member/edit_profile";
    }

    // 회원정보 수정
    @PostMapping("edit-profile")
    public String profileEdit(@Valid MemberEditDTO memberEditDTO, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("memberEditDTO", memberEditDTO);
            return "mypage/member/edit_profile";
        }

        memberService.edit(memberEditDTO);

        return "mypage/member/edit_profile";
    }

//    @PutMapping("edit-profile")
//    public ResponseEntity<String> profileEdit(@RequestBody Member member) {
//
//        memberService.edit(member);
//
//        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
//    }

//    public ResponseEntity<Integer> profileEdit(@RequestBody Member member) {
//
//        memberService.edit(member);
//        return new ResponseEntity<Integer>(HttpStatus.OK.value(), 1);
//    }

    // 회원탈퇴 페이지
    @GetMapping("withdraw")
    public String withdraw(MemberDTO memberDTO){
        return "mypage/member/withdraw";
    }

    // 회원탈퇴
    @PostMapping("withdraw")
    public String withdraw(MemberDTO memberDTO, BindingResult bindingResult, Model model,
                           HttpServletRequest request, @SessionAttribute(name = "loginMember")Member loginMember){

        Member login_checkPwd = memberService.checkPwd(loginMember.getEmail(), memberDTO.getPwd());

        if (login_checkPwd == null) {
            bindingResult.addError(new FieldError("memberLoginDTO", "pwd", "비밀번호가 일치하지 않습니다.")); // 오류 생성하고
            return "mypage/member/withdraw"; // 다시 회원탈퇴 페이지로 이동

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
