package com.soo.routine.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("member")
@Controller
public class MemberController {

    @GetMapping("delete")//delete 창을 띄움
    public String getDelete(){
        return "user/delete";
    }
    @PostMapping("delete")//delete 처리
    public String postDelete(){
        return "user/delete";
    }
    @GetMapping("delete_result")
    public String delete_result(){
        return "user/delete_result";
    }

    @GetMapping("find")
    public String getFind(){
        return "user/find";
    }
    @PostMapping("find")
    public String postFind(){
        return "user/find";
    }

    @GetMapping("join")
    public String getJoin(){
        return "user/join";
    }
    @PostMapping("join")
    public String postJoin(){
        return "user/join";
    }
    @GetMapping("join_result")
    public String getJoin_result(){
        return "user/join_result";
    }

    @GetMapping("login")
    public String getLogin(){
        return "user/member/login";
    }
    @PostMapping("login")
    public String postLogin(){
        return "user/member/login";
    }

    @GetMapping("myPage")
    public String getMyPage(){
        return "user/myPage";
    }
    @PostMapping("myPage")
    public String postMyPage(){
        return "user/myPage";
    }

    @GetMapping("update")
    public String getUpdate(){
        return "user/update";
    }
    @PostMapping("update")
    public String postUpdate(){
        return "user/update";
    }
}
