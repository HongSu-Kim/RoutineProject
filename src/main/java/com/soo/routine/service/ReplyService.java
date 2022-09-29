package com.soo.routine.service;

import com.soo.routine.dto.reply.ReplyWriteDTO;
import com.soo.routine.entity.Board;
import com.soo.routine.entity.Member;
import com.soo.routine.entity.Reply;
import com.soo.routine.mapper.ReplyMapper;
import com.soo.routine.repository.BoardRepository;
import com.soo.routine.repository.MemberRepository;
import com.soo.routine.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ReplyMapper replyMapper;
    private final ModelMapper modelMapper;

    public void createReply(ReplyWriteDTO replyWriteDTO) {

        Member member = memberRepository.findById(replyWriteDTO.getMemberId()).get();
        Board board = boardRepository.findById(replyWriteDTO.getBoardId()).get();

        Reply reply = new Reply().write(replyWriteDTO, member, board);

        replyRepository.save(reply);
    }

}
