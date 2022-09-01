package com.soo.routine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("admin")
@Controller
public class AdminController {

    @GetMapping("inquireDetail")
    public String inquireDetail() {
        return "admin/inquireDetail";
    }

    @RequestMapping("inquireList")
    public String inquireList() {
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
