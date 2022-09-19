package com.soo.routine.controller;

import com.soo.routine.dto.reply.ReplyWriteDTO;
import com.soo.routine.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("admin/reply-write")
    public String replyWrite(Model model, @Valid  ReplyWriteDTO replyWriteDTO, BindingResult bindingResult) {
        replyService.createReply(replyWriteDTO);
        return "admin/board_detail";
    }

}
