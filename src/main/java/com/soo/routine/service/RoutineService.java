package com.soo.routine.service;

import com.soo.routine.dto.RoutineDTO;
import com.soo.routine.entity.Routine;
import com.soo.routine.mapper.RoutineMapper;
import com.soo.routine.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final RoutineMapper routineMapper;
    private final ModelMapper modelMapper;

    public void createRoutine(RoutineDTO routineDTO) {
        routineRepository.save(modelMapper.map(routineDTO, Routine.class));
    }

    public List<RoutineDTO> getRoutineList(int memberNum) {
        List<RoutineDTO> lists = modelMapper.map(routineMapper.findAllByMemberNum(memberNum), new TypeToken<List<RoutineDTO>>(){}.getType());
        return lists;
    }

    public void updateData(RoutineDTO routineDTO) {

    }

}
