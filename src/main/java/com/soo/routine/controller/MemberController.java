package com.soo.routine.controller;

import com.soo.routine.dto.MemberJoinDTO;
import com.soo.routine.entity.Member;
import com.soo.routine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("profile")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("join")
    public String join(){
        return "user/profile/join";
    }
    
    @PostMapping("join")
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
            return "user/profile/join";
        }

        memberService.join(memberJoinDTO);

        return "user/profile/join";
    }

    @GetMapping("join_result")
    public String join_result(){
        return "user/profile/join_result";
    }
    @GetMapping("login")
    public String getLogin(){
        return "user/profile/login";
    }
    @PostMapping("login")
    public String postLogin(){
        return "user/profile/login";
    }
    @GetMapping("find")
    public String getFind(){
        return "user/profile/find";
    }
    @PostMapping("find")
    public String postFind(){
        return "user/profile/find";
    }

    @GetMapping("mypage")
    public String mypage(){
        return "user/profile/mypage";
    }

    @PostMapping("mypage")
    public String postMyPage(){
        return "user/profile/mypage";
    }

    @GetMapping("update")
    public String getUpdate(){
        return "user/profile/update";
    }
    @PostMapping("update")
    public String postUpdate(){
        return "user/profile/update";
    }
    @GetMapping("delete")
    public String getDelete(){
        return "user/profile/delete";
    }
    @PostMapping("delete")
    public String postDelete(){
        return "user/profile/delete";
    }
    @GetMapping("delete_result")
    public String delete_result(){
        return "user/profile/delete_result";
    }

}
