package com.soo.routine.service;

import com.soo.routine.dto.board.*;
import com.soo.routine.entity.Board;
import com.soo.routine.entity.Member;
import com.soo.routine.mapper.BoardMapper;
import com.soo.routine.repository.BoardRepository;
import com.soo.routine.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final BoardMapper boardMapper;

    // 게시글 리스트
    public List<BoardListDTO> getBoardList(String category, String memberId) {

        Type type = new TypeToken<List<BoardListDTO>>() {}.getType();

        if (memberId == null || memberId.equals(""))
            return modelMapper.map(boardRepository.findAllByCategory(category), type);
        else
            return modelMapper.map(boardRepository.findAllByCategoryAndMemberId(category, Integer.parseInt(memberId)), type);
    }

    // QnA 리스트
    public List<BoardQnaListDTO> getQnaList(String category, String memberId) {

        Type type = new TypeToken<List<BoardQnaListDTO>>() {}.getType();
        if (memberId == null || memberId.equals(""))
            return modelMapper.map(boardRepository.findAllByCategory(category), type);
        else
            return modelMapper.map(boardRepository.findAllByCategoryAndMemberId(category, Integer.parseInt(memberId)), type);
    }

    // 게시글 작성
    public void writeBoard(BoardWriteDTO boardWriteDTO) {

        Member member = memberRepository.findById(boardWriteDTO.getMemberId()).get();

        Board board = new Board().write(boardWriteDTO, member);

        boardRepository.save(board);
    }

    // 게시글 한개
    public BoardReadDTO getBoard(int boardId) {
        boardMapper.updateHits(boardId); // boardHits++
        BoardReadDTO boardReadDTO = modelMapper.map(boardRepository.findById(boardId), BoardReadDTO.class);
        return boardReadDTO;
    }

    // 게시글 수정
    public void editBoard(BoardEditDTO boardEditDTO) {

        Board board = boardRepository.findById(boardEditDTO.getBoardId()).get();

        board.edit(boardEditDTO);

        boardRepository.save(board);
    }
}
