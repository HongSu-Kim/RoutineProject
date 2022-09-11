package com.soo.routine.service;

import com.soo.routine.dto.BoardReadDTO;
import com.soo.routine.dto.BoardWriteDTO;
import com.soo.routine.entity.Board;
import com.soo.routine.repository.BoardRepository;
import com.soo.routine.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public List<BoardReadDTO> getBoardList(String category, String memberNum) {

        if (memberNum.equals(""))
            return modelMapper.map(boardRepository.findAllByCategory(category), new TypeToken<List<BoardReadDTO>>(){}.getType());
        else
            return modelMapper.map(boardRepository.findAllByCategoryAndMemberNum(category, memberNum), new TypeToken<List<BoardReadDTO>>(){}.getType());
    }

    public void createBoard(BoardWriteDTO boardWriteDTO) {

        Board board = Board.builder()
                .member(memberRepository.findById(boardWriteDTO.getMemberNum()).get())
                .category(boardWriteDTO.getCategory())
                .boardTitle(boardWriteDTO.getBoardTitle())
                .boardContent(boardWriteDTO.getBoardContent())
                .boardCreate(LocalDateTime.now())
                .boardHits(0)
                .build();

        boardRepository.save(board);
    }

    public BoardReadDTO getBoard(int boardNum) {
        BoardReadDTO boardReadDTO = modelMapper.map(boardRepository.findById(boardNum), BoardReadDTO.class);
        boardReadDTO.setNickname(memberRepository.findById(boardNum).getClass().getName());
        return boardReadDTO;
    }

}
