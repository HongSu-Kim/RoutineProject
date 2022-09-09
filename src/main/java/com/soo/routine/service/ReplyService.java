package com.soo.routine.service;

import com.soo.routine.mapper.ReplyMapper;
import com.soo.routine.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;
    private final ModelMapper modelMapper;

}
