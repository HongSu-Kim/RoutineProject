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
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final BoardMapper boardMapper;

    // 게시글 리스트
    @Transactional(readOnly = true)
    public List<BoardListDTO> getBoardList(String category) {

        Type type = new TypeToken<List<BoardListDTO>>() {}.getType();

        if (category == null)
            return modelMapper.map(boardRepository.findAll(), type);
        else
            return modelMapper.map(boardRepository.findAllByCategory(category), type);

    }

    // QnA 리스트
    @Transactional(readOnly = true)
    public List<BoardQnaListDTO> getQnaList(String category, String memberId) {

        Type type = new TypeToken<List<BoardQnaListDTO>>() {}.getType();

        if (memberId == null || memberId.equals(""))
            return modelMapper.map(boardRepository.findAllByCategory(category), type);
        else
            return modelMapper.map(boardRepository.findAllByCategoryAndMemberId(category, Long.parseLong(memberId)), type);
    }

    // 게시글 작성
    public void writeBoard(BoardWriteDTO boardWriteDTO) {

        Member member = memberRepository.findById(boardWriteDTO.getMemberId()).get();

        Board board = new Board().write(boardWriteDTO, member);

        boardRepository.save(board);
    }

    // 게시글 한개
    @Transactional(readOnly = true)
    public BoardReadDTO getBoard(Long boardId) {
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
