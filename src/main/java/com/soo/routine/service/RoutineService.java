package com.soo.routine.service;

import com.soo.routine.dto.routine.RoutineAddDTO;
import com.soo.routine.dto.routine.RoutineDTO;
import com.soo.routine.dto.routine.RoutineReadDTO;
import com.soo.routine.entity.Member;
import com.soo.routine.entity.Routine;
import com.soo.routine.mapper.RoutineMapper;
import com.soo.routine.repository.MemberRepository;
import com.soo.routine.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final MemberRepository memberRepository;
    private final RoutineMapper routineMapper;
    private final ModelMapper modelMapper;

    public void addRoutine(RoutineAddDTO routineAddDTO) {

        Member member = memberRepository.findById(routineAddDTO.getMemberId()).get();

        Routine routine = new Routine().addRecommend(routineAddDTO, member);

        routineRepository.save(routine);
    }

    public List<RoutineReadDTO> getRoutineList(int memberId) {

        List<Routine> routineList = routineRepository.findAllByMemberId(memberId);
        Type type = new TypeToken<List<RoutineReadDTO>>() {}.getType();

        List<RoutineReadDTO> lists = modelMapper.map(routineList, type);

        return lists;
    }

    public void updateData(RoutineDTO routineDTO) {

    }

}
