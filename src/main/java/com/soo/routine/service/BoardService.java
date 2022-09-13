package com.soo.routine.service;

import com.soo.routine.dto.BoardReadDTO;
import com.soo.routine.dto.BoardWriteDTO;
import com.soo.routine.entity.Board;
import com.soo.routine.entity.Member;
import com.soo.routine.repository.BoardRepository;
import com.soo.routine.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public List<BoardReadDTO> getBoardList(String category, String memberId) {

        if (memberId.equals(""))
            return modelMapper.map(boardRepository.findAllByCategory(category), new TypeToken<List<BoardReadDTO>>(){}.getType());
        else
            return modelMapper.map(boardRepository.findAllByCategoryAndMemberId(category, memberId), new TypeToken<List<BoardReadDTO>>(){}.getType());
    }

    public void writeBoard(BoardWriteDTO boardWriteDTO) {

        Member member = memberRepository.findById(boardWriteDTO.getMemberId()).get();
        Board board = new Board();
        board.write(boardWriteDTO, member);
        boardRepository.save(board);
    }

    public BoardReadDTO getBoard(int boardId) {
        BoardReadDTO boardReadDTO = modelMapper.map(boardRepository.findById(boardId), BoardReadDTO.class);
        boardReadDTO.setNickname(memberRepository.findById(boardId).getClass().getName());
        return boardReadDTO;
    }

}
