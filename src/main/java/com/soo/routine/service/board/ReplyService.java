package com.soo.routine.service.board;

import com.soo.routine.dto.board.ReplyWriteDTO;
import com.soo.routine.entity.board.Board;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.board.Reply;
import com.soo.routine.mapper.board.ReplyMapper;
import com.soo.routine.repository.board.BoardRepository;
import com.soo.routine.repository.member.MemberRepository;
import com.soo.routine.repository.board.ReplyRepository;
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
