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
        return "routine/delete";
    }
    @PostMapping("delete")//delete 처리
    public String postDelete(){
        return "routine/delete";
    }
    @GetMapping("delete_result")
    public String delete_result(){
        return "routine/delete_result";
    }

    @GetMapping("find")
    public String getFind(){
        return "routine/find";
    }
    @PostMapping("find")
    public String postFind(){
        return "routine/find";
    }

    @GetMapping("join")
    public String getJoin(){
        return "routine/join";
    }
    @PostMapping("join")
    public String postJoin(){
        return "routine/join";
    }
    @GetMapping("join_result")
    public String getJoin_result(){
        return "routine/join_result";
    }

    @GetMapping("login")
    public String getLogin(){
        return "routine/login";
    }
    @PostMapping("login")
    public String postLogin(){
        return "routine/login";
    }

    @GetMapping("myPage")
    public String getMyPage(){
        return "routine/myPage";
    }
    @PostMapping("myPage")
    public String postMyPage(){
        return "routine/myPage";
    }

    @GetMapping("update")
    public String getUpdate(){
        return "routine/update";
    }
    @PostMapping("update")
    public String postUpdate(){
        return "routine/update";
    }
}
