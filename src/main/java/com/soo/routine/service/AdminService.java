package com.soo.routine.service;

import com.soo.routine.domain.Board;
import com.soo.routine.dto.BoardDTO;
import com.soo.routine.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminMapper adminMapper;
    private final ModelMapper modelMapper;

    public BoardDTO getBoard(int boardNum) {
        return modelMapper.map(adminMapper.getBoard(boardNum), BoardDTO.class);
    }

    public List<BoardDTO> getBoardList(String categoryName) {
//        return modelMapper.map(adminMapper.getBoardList(categoryName), List<Board>);
        return null;
    }





}
