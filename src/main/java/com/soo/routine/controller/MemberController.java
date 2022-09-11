package com.soo.routine.controller;

import com.soo.routine.dto.MemberJoinDTO;
import com.soo.routine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;

@RequestMapping("profile")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("join")
    public String join(MemberJoinDTO memberJoinDTO){//회원가입 창을 띄움
        return "user/profile/join";
    }
    
    @PostMapping("join")
    public String join(@Valid MemberJoinDTO memberJoinDTO, BindingResult bindingResult){

        //검증 실패시
        if(bindingResult.hasErrors()){
            return "user/profile/join";
        }

        //회원가입 로직
        memberService.join(memberJoinDTO.getEmail(), memberJoinDTO.getPwd(),
                memberJoinDTO.getNickname(), memberJoinDTO.getGender(), LocalDate.parse(memberJoinDTO.getBirth()));

        if(!memberJoinDTO.getPwd().equals(memberJoinDTO.getPwd2())){
            bindingResult.reject("pwd2", "패스워드가 일치하지 않습니다.");
            return  "user/profile/join";
        }

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
    @GetMapping("myPage")
    public String getMyPage(){
        return "user/profile/myPage";
    }
    @PostMapping("myPage")
    public String postMyPage(){
        return "user/profile/myPage";
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
