package com.soo.routine.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    void updateHits(int boardId);
}
