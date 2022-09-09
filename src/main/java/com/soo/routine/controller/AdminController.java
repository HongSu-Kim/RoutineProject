package com.soo.routine.controller;

import com.soo.routine.dto.BoardDTO;
import com.soo.routine.dto.MemberDTO;
import com.soo.routine.dto.ReplyDTO;
import com.soo.routine.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("admin")
@RequiredArgsConstructor
@Controller
public class AdminController {

    private final AdminService adminService;

    @GetMapping("board-list")
    public String boardList(Model model, @RequestParam(defaultValue = "") String categoryName) {
        List<BoardDTO> lists = adminService.getBoardList(categoryName);
        model.addAttribute("lists", lists);
        model.addAttribute("categoryName", categoryName);
        return "admin/board_list";
    }

    @GetMapping("board-write")
    public String boardWrite() {
        return "admin/board_write";
    }

    @PostMapping("board-write")
    public String boardWrite(Model model, BoardDTO boardDTO) {
        adminService.createBoard(boardDTO);
        model.addAttribute("categoryName", boardDTO.getCategoryName());
        return "redirect:/admin/board-list";
    }

    @GetMapping("board-detail")
    public String boardDetail(Model model, int boardNum) {
        BoardDTO boardDTO = adminService.getBoard(boardNum);
        model.addAttribute("boardDTO", boardDTO);
        return "admin/board_detail";
    }

    @PostMapping("reply-write")
    public String replyWrite(Model model, ReplyDTO replyDTO) {
        adminService.createReply(replyDTO);
        return "admin/board_detail";
    }

    @GetMapping("member-list")
    public String memberList(Model model, String grade) {
        List<MemberDTO> lists = adminService.getMemberList(grade);
        model.addAttribute("lists", lists);
        return "admin/member_list";
    }

    @GetMapping("routine-list")
    public String routineList() {
        return "admin/routine_list";
    }

    @GetMapping("mission-list")
    public String missionList() {
        return "admin/mission_list";
    }


}
