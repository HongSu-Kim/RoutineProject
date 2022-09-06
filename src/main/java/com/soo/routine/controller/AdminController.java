package com.soo.routine.controller;

import com.soo.routine.dto.BoardDTO;
import com.soo.routine.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin")
@RequiredArgsConstructor
@Controller
public class AdminController {

    private final AdminService adminService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

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
        adminService.createData(boardDTO);
        model.addAttribute("categoryName", boardDTO.getCategoryName());
        return "redirect:/admin/board-list";
    }

    @GetMapping("board-detail")
    public String boardDetail(Model model, int boardNum) {
        BoardDTO boardDTO = adminService.getBoard(boardNum);
        model.addAttribute("boardDTO", boardDTO);
        return "admin/board_detail";
    }

    @GetMapping("reply-write")
    public String replyWrite(Model model, int boardNum) {
        BoardDTO boardDTO = adminService.getBoard(boardNum);
        model.addAttribute("boardDTO", boardDTO);
        return "admin/board_detail";
    }

    @GetMapping("member-list")
    public String memberList() {
        return "admin/member_list";
    }

    @GetMapping("admin-list")
    public String adminList() {
        return "admin/admin_list";
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
