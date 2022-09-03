package com.soo.routine.service;

import com.soo.routine.dao.BoardDAO;
import com.soo.routine.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardDAO dao;

    public List<BoardDTO> getList(String categoryName) {
        return dao.getList(categoryName);
    }



}
