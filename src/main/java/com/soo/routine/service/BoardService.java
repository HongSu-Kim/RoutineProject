package com.soo.routine.service;

import com.soo.routine.mapper.BoardMapper;
import com.soo.routine.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final ModelMapper modelMapper;

}
