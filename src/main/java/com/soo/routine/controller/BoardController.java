package com.soo.routine.controller;

import com.soo.routine.dto.BoardReadDTO;
import com.soo.routine.dto.BoardWriteDTO;
import com.soo.routine.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("admin/board-list")
    public String boardList(Model model, String category, @RequestParam(defaultValue = "") String memberId) {
        List<BoardReadDTO> lists = boardService.getBoardList(category, memberId);
        model.addAttribute("lists", lists);
        model.addAttribute("category", category);
        return "admin/board_list";
    }

    @GetMapping("admin/board-write")
    public String boardWrite() {
        return "admin/board_write";
    }

    @PostMapping("admin/board-write")
    public String boardWrite(Model model, @Valid BoardWriteDTO boardWriteDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "admin/board-write";
        }

        boardService.writeBoard(boardWriteDTO);

        return "redirect:/admin/board-list?category=" + boardWriteDTO.getCategory();
    }

    @GetMapping("admin/board-detail")
    public String boardDetail(Model model, int boardId) {
        BoardReadDTO boardReadDTO = boardService.getBoard(boardId);
        model.addAttribute("boardDTO", boardReadDTO);
        return "admin/board_detail";
    }

}
