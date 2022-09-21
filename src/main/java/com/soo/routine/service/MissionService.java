package com.soo.routine.service;

import com.soo.routine.dto.mission.MissionAddDTO;
import com.soo.routine.dto.mission.MissionAddRecommendDTO;
import com.soo.routine.dto.mission.MissionReadDTO;
import com.soo.routine.entity.Mission;
import com.soo.routine.entity.MissionIcon;
import com.soo.routine.entity.Routine;
import com.soo.routine.mapper.MissionMapper;
import com.soo.routine.repository.MissionIconRepository;
import com.soo.routine.repository.MissionRepository;
import com.soo.routine.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionIconRepository missionIconRepository;
    private final RoutineRepository routineRepository ;
    private final MissionMapper missionMapper;
    private final ModelMapper modelMapper;

    // 추천 미션 추가
    public void addRecommendMission(MissionAddRecommendDTO missionAddRecommendDTO) {

        MissionIcon missionIcon = missionIconRepository.findById(missionAddRecommendDTO.getIconId()).get();

        Mission mission = new Mission().addRecommend(missionAddRecommendDTO, missionIcon);

        missionRepository.save(mission);
    }

    // 미션 리스트
    public List<MissionReadDTO> getMissionList(String routineId) {

        List<Mission> missionList;
        Type type = new TypeToken<List<MissionReadDTO>>() {}.getType();

        if (routineId == null || routineId.equals("")) {
            missionList = missionRepository.findAllByRoutineMemberLevel("admin");
        } else {
            missionList = missionRepository.findAllByRoutineId(Integer.parseInt(routineId));
        }

        List<MissionReadDTO> lists = modelMapper.map(missionList, type);

        return lists;
    }

    // 미션 추가
    public void addMission(MissionAddDTO missionAddDTO) {

        Routine routine = routineRepository.findById(missionAddDTO.getRoutineId()).get();
        MissionIcon missionIcon = missionIconRepository.findById(missionAddDTO.getIconId()).get();

        Mission mission = new Mission().add(missionAddDTO, routine, missionIcon);

        missionRepository.save(mission);
    }

}
