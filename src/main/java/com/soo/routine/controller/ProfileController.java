package com.soo.routine.controller;

import com.soo.routine.dto.MemberLoginDTO;
import com.soo.routine.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("profile")
@RequiredArgsConstructor
@Controller
public class ProfileController {

    private final ProfileService profileService;

    //board
    @GetMapping("board-list")
    public String getBoardList(){
        return "user/profile/board_list";
    }
    @PostMapping("board-list")
    public String postBoardList(){
        return "user/profile/board_list";
    }
    @GetMapping("board-detail")
    public String getBoardDetail(){
        return "user/profile/board_detail";
    }
    @PostMapping("board-detail")
    public String postBoardDetail(){
        return "user/profile/board_detail";
    }
    @GetMapping("board-write")
    public String getBoardWrite(){
        return "user/profile/board_write";
    }
    @PostMapping("board-write")
    public String postBoardWrite(){
        return "user/profile/board_write";
    }

    //member
    @GetMapping("join")
    public String getJoin(MemberLoginDTO memberDTO){
        return "user/profile/join";
    }
    @PostMapping("join")
    public String postJoin(@Validated MemberLoginDTO memberDTO){

        return "user/profile/join";
    }
    @GetMapping("join_result")
    public String getJoin_result(){
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
