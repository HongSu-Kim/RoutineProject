package com.soo.routine.controller;

import com.soo.routine.dto.BoardDTO;
import com.soo.routine.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("admin")
@RequiredArgsConstructor
@Controller
public class AdminController {

    private final AdminService adminService;

    @GetMapping("board-detail")
    public String boardDetail(Model model, HttpServletRequest request) {
        int boardNum = Integer.parseInt(request.getParameter("boardNum"));
        BoardDTO boardDTO = adminService.getBoard(boardNum);
        model.addAttribute(boardDTO);
        return "admin/board_detail";
    }

    @GetMapping("board-list")
    public String boardList(Model model, HttpServletRequest request) {
        String categoryName = request.getParameter("categoryName");
        List<BoardDTO> lists = adminService.getBoardList(categoryName);
        model.addAttribute("lists", lists);
        return "admin/board_list";
    }

    @GetMapping("board-write")
    public String boardWrite() {

        return "admin/board_write";
    }

    @RequestMapping("member-list")
    public String memberList() {
        return "admin/member_list";
    }


}
