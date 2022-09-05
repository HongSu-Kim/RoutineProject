package com.soo.routine.mapper;

import com.soo.routine.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<Board> getList(String categoryName);
    void createData(Board board);
    Board getData(int boardNum);



}
