package com.soo.routine.mapper;

import com.soo.routine.domain.Board;
import com.soo.routine.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    Board getBoard(int boardNum);
//    List<Board> getBoardList(String categoryName);



}
