package com.soo.routine.service;

import com.soo.routine.domain.Board;
import com.soo.routine.dto.BoardDTO;
import com.soo.routine.mapper.BoardMapper;
import com.soo.routine.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final BoardMapper boardMapper;
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    public List<BoardDTO> getBoardList(String categoryName) {
        return modelMapper.map(boardMapper.getList(categoryName), new TypeToken<List<BoardDTO>>(){}.getType());
    }

    public void createData(BoardDTO boardDTO) {
        boardMapper.createData(modelMapper.map(boardDTO, Board.class));
    }

    public BoardDTO getBoard(int boardNum) {
        BoardDTO boardDTO = modelMapper.map(boardMapper.getData(boardNum), BoardDTO.class);
//        boardDTO.setNickName(memberMapper.);
        return boardDTO;
    }





}
