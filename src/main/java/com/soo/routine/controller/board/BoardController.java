package com.soo.routine.controller.board;

import com.soo.routine.dto.board.*;
import com.soo.routine.dto.board.ReplyWriteDTO;
import com.soo.routine.service.board.BoardService;
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

    /*
    Admin Page
    */

    // 게시글 리스트 페이지
    @GetMapping("admin/board-list")
    public String boardList(Model model, String boardCategory, String memberId) {

        model.addAttribute("boardCategory", boardCategory);
        model.addAttribute("pageName", boardCategory + " List");

        if (boardCategory == null || !boardCategory.equals("QnA")) {
            List<BoardListDTO> lists = boardService.getBoardList(boardCategory);
            model.addAttribute("lists", lists);
            return "admin/board_list";
        } else {
            List<BoardQnaListDTO> lists = boardService.getQnaList(boardCategory, memberId);
            model.addAttribute("lists", lists);
            return "admin/qna_list";
        }
    }

    // 게시글 작성 페이지
    @GetMapping("admin/board-write")
    public String boardWrite(Model model, BoardWriteDTO boardWriteDTO, String boardCategory) {

        model.addAttribute("boardCategory", boardCategory);
        model.addAttribute("pageName", "Board Write");
        return "admin/board_write";
    }

    // 게시글 작성
    @PostMapping("admin/board-write")
    public String boardWrite(Model model, @Valid BoardWriteDTO boardWriteDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            model.addAttribute("pageName", "Board Write");
            return "admin/board_write";
        }

        boardService.writeBoard(boardWriteDTO);

        return "redirect:/admin/board-list?boardCategory=" + boardWriteDTO.getCategory();
    }

    // 게시글 디테일 페이지
    @GetMapping("admin/board-detail")
    public String boardDetail(Model model, ReplyWriteDTO replyWriteDTO, Long boardId) {

        BoardReadDTO boardReadDTO = boardService.getBoard(boardId);
        String category = boardReadDTO.getCategory();

        model.addAttribute("boardDTO", boardReadDTO);
        model.addAttribute("boardCategory", category);
        model.addAttribute("pageName", "Board Detail");

        if (category.equals("QnA"))
            return "admin/qna_detail";
        else
            return "admin/board_detail";
    }

    // 게시글 수정 페이지
    @GetMapping("admin/board-edit")
    public String boardEdit(Model model, BoardEditDTO boardEditDTO, Long boardId) {

        BoardReadDTO boardReadDTO = boardService.getBoard(boardId);

        model.addAttribute("boardEditDTO", boardReadDTO);
        model.addAttribute("pageName", "Board Edit");
        return "admin/board_edit";
    }

    // 게시글 수정
    @PostMapping("admin/board-edit")
    public String boardEdit(Model model, @Valid BoardEditDTO boardEditDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            model.addAttribute("pageName", "Board Edit");
            return "admin/board_edit";
        }

        boardService.editBoard(boardEditDTO);

        return "redirect:/admin/board-detail?boardId=" + boardEditDTO.getBoardId();
    }

    /*
    User Page
    */
    @GetMapping("mypage/board-list")
    public String getBoardList(){
        return "mypage/board_list";
    }
    @PostMapping("mypage/board-list")
    public String postBoardList(){
        return "mypage/board_list";
    }

    @GetMapping("mypage/board-detail")
    public String getBoardDetail(){
        return "mypage/board_detail";
    }
    @PostMapping("mypage/board-detail")
    public String postBoardDetail(){
        return "mypage/board_detail";
    }

    @GetMapping("mypage/board-write")
    public String getBoardWrite(){
        return "mypage/board_write";
    }
    @PostMapping("mypage/board-write")
    public String postBoardWrite(){
        return "mypage/board_write";
    }

    @GetMapping("mypage/board-edit")
    public String getBoardEdit(){
        return "mypage/board_edit";
    }
    @PostMapping("mypage/board-edit")
    public String postBoardEdit(){
        return "mypage/board_edit";
    }

    @GetMapping("mypage/board-delete")
    public String getBoardDelete(){
        return "mypage/board_delete";
    }
    @PostMapping("mypage/board-delete")
    public String postBoardDelete(){
        return "mypage/board_delete";
    }

}
