package com.soo.routine.controller;

import com.soo.routine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

}
