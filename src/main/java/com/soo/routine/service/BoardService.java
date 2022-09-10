package com.soo.routine.service;

import com.soo.routine.dto.BoardReadDTO;
import com.soo.routine.dto.BoardWriteDTO;
import com.soo.routine.entity.Board;
import com.soo.routine.mapper.BoardMapper;
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
    private final BoardMapper boardMapper;
    private final ModelMapper modelMapper;

    public List<BoardReadDTO> getBoardList(String category, String memberNum) {
        return modelMapper.map(boardMapper.findAllByCategoryAndMemberNum(category, memberNum), new TypeToken<List<BoardReadDTO>>(){}.getType());
    }

    public void createBoard(BoardWriteDTO boardWriteDTO) {
        boardRepository.save(modelMapper.map(boardWriteDTO, Board.class));
    }

    public BoardReadDTO getBoard(int boardNum) {
        BoardReadDTO boardReadDTO = modelMapper.map(boardRepository.findById(boardNum), BoardReadDTO.class);
        boardReadDTO.setNickname(memberRepository.findById(boardNum).getClass().getName());
        return boardReadDTO;
    }

}
