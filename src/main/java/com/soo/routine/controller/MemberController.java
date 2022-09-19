package com.soo.routine.controller;

import com.soo.routine.dto.member.MemberJoinDTO;
import com.soo.routine.dto.member.MemberReadDTO;
import com.soo.routine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("profile/join")
    public String join(){
        return "user/profile/join";
    }
    
    @PostMapping("profile/join")
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

    @GetMapping("profile/join_result")
    public String join_result(){
        return "user/profile/join_result";
    }
    @GetMapping("profile/login")
    public String getLogin(){
        return "user/profile/login";
    }
    @PostMapping("profile/login")
    public String postLogin(){
        return "user/profile/login";
    }
    @GetMapping("profile/find")
    public String getFind(){
        return "user/profile/find";
    }
    @PostMapping("profile/find")
    public String postFind(){
        return "user/profile/find";
    }

    @GetMapping("profile/mypage")
    public String mypage(){
        return "user/profile/mypage";
    }

    @PostMapping("profile/mypage")
    public String postMyPage(){
        return "user/profile/mypage";
    }

    @GetMapping("profile/update")
    public String getUpdate(){
        return "user/profile/update";
    }
    @PostMapping("profile/update")
    public String postUpdate(){
        return "user/profile/update";
    }
    @GetMapping("profile/delete")
    public String getDelete(){
        return "user/profile/delete";
    }
    @PostMapping("profile/delete")
    public String postDelete(){
        return "user/profile/delete";
    }
    @GetMapping("profile/delete_result")
    public String delete_result(){
        return "user/profile/delete_result";
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
