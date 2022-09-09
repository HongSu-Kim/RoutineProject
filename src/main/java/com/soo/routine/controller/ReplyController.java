package com.soo.routine.controller;

import com.soo.routine.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;

}
