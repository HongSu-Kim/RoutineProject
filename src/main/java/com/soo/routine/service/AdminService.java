//package com.soo.routine.service;
//
//import com.soo.routine.entity.Board;
//import com.soo.routine.entity.Reply;
//import com.soo.routine.dto.board.BoardDTO;
//import com.soo.routine.dto.MemberDTO;
//import com.soo.routine.dto.ReplyDTO;
//import com.soo.routine.mapper.BoardMapper;
//import com.soo.routine.mapper.MemberMapper;
//import com.soo.routine.mapper.ReplyMapper;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class AdminService {
//
//    private final ModelMapper modelMapper;
//    private final BoardMapper boardMapper;
//    private final MemberMapper memberMapper;
//    private final ReplyMapper replyMapper;
//
//    public List<BoardDTO> getBoardList(String categoryName) {
//        return modelMapper.map(boardMapper.getList(categoryName), new TypeToken<List<BoardDTO>>(){}.getType());
//    }
//
//    public void createBoard(BoardDTO boardDTO) {
//        boardMapper.createData(modelMapper.map(boardDTO, Board.class));
//    }
//
//    public BoardDTO getBoard(int boardNum) {
//        BoardDTO boardDTO = modelMapper.map(boardMapper.getData(boardNum), BoardDTO.class);
//        boardDTO.setNickname(memberMapper.getNickname(boardNum));
//        return boardDTO;
//    }
//
//    public void createReply(ReplyDTO replyDTO) {
//        replyMapper.createData(modelMapper.map(replyDTO, Reply.class));
//    }
//
//    public List<MemberDTO> getMemberList(String grade) {
//        return modelMapper.map(memberMapper.getList(grade), new TypeToken<MemberDTO>(){}.getType());
//    }
//
//
//
//}
