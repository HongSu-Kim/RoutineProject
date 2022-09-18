package com.soo.routine.controller;

import com.soo.routine.dto.board.BoardEditDTO;
import com.soo.routine.dto.board.BoardListDTO;
import com.soo.routine.dto.board.BoardReadDTO;
import com.soo.routine.dto.board.BoardWriteDTO;
import com.soo.routine.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    // 게시글 리스트 페이지
    @GetMapping("admin/board-list")
    public String boardList(Model model, String boardCategory, String memberId) {
        List<BoardListDTO> lists = boardService.getBoardList(boardCategory, memberId);
        model.addAttribute("lists", lists);
        model.addAttribute("boardCategory", boardCategory);
        model.addAttribute("pageName", boardCategory + " List");
        return "admin/board_list";
    }

    // 게시글 작성 페이지
    @GetMapping("admin/board-write")
    public String boardWrite(Model model, String boardCategory, BoardWriteDTO boardWriteDTO) {
        model.addAttribute("mode", "write");
        model.addAttribute("boardCategory", boardCategory);
        model.addAttribute("pageName", "Board Write");
        return "admin/board_write";
    }

    // 게시글 작성
    @PostMapping("admin/board-write")
    public String boardWrite(Model model, @Valid BoardWriteDTO boardWriteDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            model.addAttribute("mode", "write");
            model.addAttribute("boardDTO", boardWriteDTO);
            model.addAttribute("pageName", "Board Write");
            return "admin/board_write";
        }

        boardService.writeBoard(boardWriteDTO);

        return "redirect:/admin/board-list?boardCategory=" + boardWriteDTO.getCategory();
    }

    // 게시글 디테일 페이지
    @GetMapping("admin/board-detail")
    public String boardDetail(Model model, int boardId) {
        BoardReadDTO boardReadDTO = boardService.getBoard(boardId);
        model.addAttribute("boardDTO", boardReadDTO);
        model.addAttribute("boardCategory", boardReadDTO.getCategory());
        model.addAttribute("pageName", "Board Detail");
        return "admin/board_detail";
    }

    // 게시글 수정 페이지
    @GetMapping("admin/board-edit")
    public String boardEdit(Model model, int boardId, BoardEditDTO boardEditDTO){
        BoardReadDTO boardReadDTO = boardService.getBoard(boardId);

        model.addAttribute("mode", "edit");
        model.addAttribute("boardDTO", boardReadDTO);
        model.addAttribute("pageName", "Board Edit");
        return "admin/board_write";
    }

    // 게시글 수정
    @PostMapping("admin/board-edit")
    public String boardEdit(Model model, @Valid BoardEditDTO boardEditDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            model.addAttribute("boardDTO", boardEditDTO);
            model.addAttribute("pageName", "Board Edit");
            return "admin/board-edit";
        }

        boardService.editBoard(boardEditDTO);

        return "redirect:/admin/board-detail?boardId=" + boardEditDTO.getBoardId();
    }

}
