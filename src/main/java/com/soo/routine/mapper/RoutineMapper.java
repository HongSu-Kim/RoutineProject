package com.soo.routine.mapper;

import com.soo.routine.entity.Routine;
import com.soo.routine.dto.RoutineDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoutineMapper {

    void createData(Routine routine);
    List<RoutineDTO> getList(int memberId);
}
