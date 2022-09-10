package com.soo.routine.mapper;

import com.soo.routine.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<Board> findAllByCategoryAndMemberNum(String category, String memberNum);
}
