package com.soo.routine.mapper;

import com.soo.routine.entity.Routine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoutineMapper {

    List<Routine> findAllByMemberNum(int memberNum);
}
