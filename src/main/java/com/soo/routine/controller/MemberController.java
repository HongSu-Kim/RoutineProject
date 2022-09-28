package com.soo.routine.controller;

import com.soo.routine.dto.member.MemberJoinDTO;
import com.soo.routine.dto.member.MemberLoginDTO;
import com.soo.routine.dto.member.MemberReadDTO;
import com.soo.routine.entity.Member;
import com.soo.routine.repository.MemberRepository;
import com.soo.routine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

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
    public String mypage(@AuthenticationPrincipal Member member, Authentication authentication, Principal principal) throws Exception {
        return "mypage/member_mypage";
    }

    @GetMapping("mypage/login")
    public String login(MemberLoginDTO memberLoginDTO) {
        return "mypage/member_login";
    }
    @PostMapping("mypage/login")
    public String login(@Valid @ModelAttribute() MemberLoginDTO memberLoginDTO, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("memberLoginDTO", memberLoginDTO);
            return "mypage/member_login";
        }

        Member login_checkEmail = memberService.checkEmail(memberLoginDTO.getEmail());
        Member login_checkPwd = memberService.checkPwd(memberLoginDTO.getEmail(), memberLoginDTO.getPwd()); // service를 호출해서

        if (login_checkEmail == null) { // 로그인 실패 시
            bindingResult.addError(new FieldError("memberLoginDTO", "email", "이메일이 존재하지 않습니다.")); // 오류 생성하고
            return "mypage/member_login"; // 다시 로그인 페이지로 이동
        }

        if (login_checkPwd == null) { // 로그인 실패 시
            bindingResult.addError(new FieldError("memberLoginDTO", "pwd", "비밀번호가 일치하지 않습니다.")); // 오류 생성하고
            return "mypage/member_login"; // 다시 로그인 페이지로 이동
        }
        return "redirect:/mypage"; // 로그인 성공 시 마이페이지로 이동
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
    public String join(MemberJoinDTO memberJoinDTO){
        return "mypage/member_join";
    }
    @PostMapping("mypage/join")
    public String join(@Valid @ModelAttribute() MemberJoinDTO memberJoinDTO, BindingResult bindingResult, Model model){

        ModelMapper modelMapper = new ModelMapper();
//        Member member = modelMapper.map(memberJoinDTO, Member.class);
        Member member = new Member("member", LocalDateTime.now(),
                memberJoinDTO.getEmail(), memberJoinDTO.getPwd(), memberJoinDTO.getNickname(),
                memberJoinDTO.getGender(), memberJoinDTO.getBirth());
        member.setPwd(passwordEncoder.encode(member.getPwd()));
        memberService.join(member);

        //검증 실패시
        if(bindingResult.hasErrors()){
            model.addAttribute("memberJoinDTO", memberJoinDTO);
            return "mypage/member_join";
        }

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

        return "redirect:/mypage/login";
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
