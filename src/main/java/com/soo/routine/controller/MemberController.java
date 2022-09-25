package com.soo.routine.controller;

import com.soo.routine.dto.member.MemberJoinDTO;
import com.soo.routine.dto.member.MemberLoginDTO;
import com.soo.routine.dto.member.MemberReadDTO;
import com.soo.routine.entity.Member;
import com.soo.routine.repository.MemberRepository;
import com.soo.routine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /*
    Start Page
    */
    @GetMapping("startRoutine")
    public String getStartRoutine(){
        return "mypage/startRoutine";
    }
    @PostMapping("startRoutine")
    public String postStartRoutine(){
        return "mypage/startRoutine";
    }

    /*
    User Page
    */
    @GetMapping("mypage")
    public String mypage(){
        return "mypage/member_mypage";
    }
    @PostMapping("mypage")
    public String postMyPage(){
        return "mypage/member_mypage";
    }

    @GetMapping("mypage/login")
    public String login(@ModelAttribute("loginForm") MemberLoginDTO form) {
        return "mypage/member_login";
    }
    @PostMapping("mypage/login")
    public String login(@Valid @ModelAttribute MemberLoginDTO form, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "mypage/member_login";
        }

        return "mypage/member_login";
    }

    @GetMapping("mypage/resetPwd")
    public String getFind(){
        return "mypage/member_resetPwd";
    }
    @PostMapping("mypage/resetPwd")
    public String postFind(){
        return "mypage/member_resetPwd";
    }

    @GetMapping("mypage/join")
    public String join(){
        return "mypage/member_join";
    }
    @PostMapping("mypage/join")
    public String join(@Valid MemberJoinDTO memberJoinDTO, BindingResult bindingResult, Model model){

        //패스워드 일치 여부 체크
        if(!memberJoinDTO.getPwd().equals(memberJoinDTO.getPwd2())){
            bindingResult.addError(new FieldError("memberJoinDTO", "pwd2", "패스워드가 일치하지 않습니다."));
        }

        //이메일 중복 체크
        try {
            memberService.validateDuplicateMemberEmail(memberJoinDTO);
        } catch (IllegalStateException e) {
            bindingResult.addError(new FieldError("memberJoinDTO", "email", "중복된 이메일입니다"));
        }

        //닉네임 중복 체크
        try {
            memberService.validateDuplicateMemberNickname(memberJoinDTO);
        } catch (IllegalStateException e) {
            bindingResult.addError(new FieldError("memberJoinDTO", "nickname", "중복된 닉네임입니다"));
        }

        //검증 실패시
        if(bindingResult.hasErrors()){
            model.addAttribute("memberJoinDTO", memberJoinDTO);
            return "user/mypage/join";
        }

        memberService.join(memberJoinDTO);

        return "mypage/member_join";
    }

    @GetMapping("mypage/editProfile")
    public String getUpdate(){
        return "mypage/member_editProfile";
    }
    @PostMapping("mypage/editProfile")
    public String postUpdate(){
        return "mypage/member_editProfile";
    }

    @GetMapping("mypage/withdraw")
    public String getDelete(){
        return "mypage/member_withdraw";
    }
    @PostMapping("mypage/withdraw")
    public String postDelete(){
        return "mypage/member_withdraw";
    }

    /*
    Admin Page
    */
    @GetMapping("admin/user-list")
    public String memberList(Model model, String level) {

        List<MemberReadDTO> lists = memberService.getMemberList(level);

        model.addAttribute("lists", lists);
//        model.addAttribute("level", level);
        model.addAttribute("pageName", level + " List");
        return "admin/user_list";
    }

}
