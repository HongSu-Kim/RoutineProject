package com.soo.routine.controller.board;

import com.soo.routine.dto.board.ReplyWriteDTO;
import com.soo.routine.service.board.ReplyService;
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

    // 답글 작성
    @PostMapping("admin/reply-write")
    public String replyWrite(Model model, @Valid ReplyWriteDTO replyWriteDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(bindingResult);
            model.addAttribute("replyDTO", replyWriteDTO);
            return "admin/board/detail";
        }

        replyService.createReply(replyWriteDTO);
        return "redirect:/admin/board-detail?boardId=" + replyWriteDTO.getBoardId();
    }

}
