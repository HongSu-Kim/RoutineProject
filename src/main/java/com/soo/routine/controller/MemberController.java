package com.soo.routine.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("member")//http://localhost:8080/ 다음에 들어갈 주소
    public String member(Model model){

        model.addAttribute("data", "member!!");//data에 'member!' 값을 넣음

        return "layout/nav_member";//application.properties에서 앞뒤 경로를 미리 설정해둠
    }
}
