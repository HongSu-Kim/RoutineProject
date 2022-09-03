package com.soo.routine.controller;

import com.soo.routine.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("admin")
@RequiredArgsConstructor
@Controller
public class AdminController {

    private final AdminService boardService;

    @GetMapping("inquireDetail")
    public String inquireDetail() {
        return "admin/inquireDetail";
    }

    @GetMapping("inquireList")
    public String inquireList(Model model) {
//        List<BoardDTO> lists = boardService.getList("inquire");
//        model.addAttribute("lists", lists);
        return "admin/inquireList";
    }

    @RequestMapping("memberList")
    public String memberList() {
        return "admin/memberList";
    }

    @RequestMapping("missionList")
    public String missionList() {
        return "admin/missionList";
    }

    @RequestMapping("routineList")
    public String routineList() {
        return "admin/routineList";
    }


}
