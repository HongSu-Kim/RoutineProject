package com.soo.routine.service.routine;

import com.soo.routine.dto.routine.RoutineAddDTO;
import com.soo.routine.dto.routine.RoutineReadDTO;
import com.soo.routine.dto.routine.RoutineUpdateDTO;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.mission.Mission;
import com.soo.routine.entity.mission.MissionIcon;
import com.soo.routine.entity.routine.Routine;
import com.soo.routine.entity.routine.RoutineSet;
import com.soo.routine.mapper.routine.RoutineMapper;
import com.soo.routine.repository.member.MemberRepository;
import com.soo.routine.repository.mission.MissionIconRepository;
import com.soo.routine.repository.mission.MissionRepository;
import com.soo.routine.repository.routine.RoutineRepository;
import com.soo.routine.repository.routine.RoutineSetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final RoutineSetRepository routineSetRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MissionIconRepository missionIconRepository;
    private final RoutineMapper routineMapper;
    private final ModelMapper modelMapper;

    // 루틴 추가
    public void addRoutine(RoutineAddDTO routineAddDTO) {

        Member member = memberRepository.findById(routineAddDTO.getMemberId()).get();

        // save routine
        Routine routine = new Routine().addRoutine(routineAddDTO, member);
        routineRepository.save(routine);

        // save routineSet
        boolean[] weekActive = routineAddDTO.getWeekActive();
        String startTime = routineAddDTO.getStartTime();
        for (int i = 0; i < 7; i++) {
            RoutineSet routineSet = new RoutineSet().addRoutineSet(i , weekActive[i], startTime, routine);
            routineSetRepository.save(routineSet);
        }

        // save mission
        if (routineAddDTO.getIconId() != null) {
            for (int i = 0; i < routineAddDTO.getIconId().length; i++) {
                MissionIcon missionIcon = missionIconRepository.findById(routineAddDTO.getIconId()[i]).get();
                Mission mission = new Mission().addRecommend(routine, missionIcon, routineAddDTO.getMissionName()[i],
                routineAddDTO.getRuntime()[i], routineAddDTO.getMissionContent()[i] != null ? routineAddDTO.getMissionContent()[i] : "");
                missionRepository.save(mission);
            }
        }
    }

    // 루틴 리스트
    @Transactional(readOnly = true)
    public List<RoutineReadDTO> getRoutineList(Long memberId) {

        List<Routine> routineList = routineRepository.findAllByMemberId(memberId);
        Type type = new TypeToken<List<RoutineReadDTO>>() {}.getType();

        List<RoutineReadDTO> lists = modelMapper.map(routineList, type);

        return lists;
    }
    
    // 루틴 수정
    public void updateRoutine(RoutineUpdateDTO routineUpdateDTO) {

        Routine routine = routineRepository.findById(routineUpdateDTO.getRoutineId()).get();

        routineRepository.save(routine);
    }

    // 루틴 추천 리스트
    @Transactional(readOnly = true)
    public List<RoutineReadDTO> getRecommendRoutineList() {

        List<Routine> routineList = routineRepository.findAllByMemberLevel("admin");
        Type type = new TypeToken<List<RoutineReadDTO>>() {}.getType();

        List<RoutineReadDTO> lists = modelMapper.map(routineList, type);

        return lists;
    }


}
