package com.soo.routine.service.mission;

import com.soo.routine.dto.mission.*;
import com.soo.routine.entity.mission.Mission;
import com.soo.routine.entity.mission.MissionIcon;
import com.soo.routine.entity.routine.Routine;
import com.soo.routine.mapper.mission.MissionMapper;
import com.soo.routine.repository.mission.MissionIconRepository;
import com.soo.routine.repository.mission.MissionRepository;
import com.soo.routine.repository.routine.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionIconRepository missionIconRepository;
    private final RoutineRepository routineRepository ;
    private final MissionMapper missionMapper;
    private final ModelMapper modelMapper;

    // 추천 미션 추가
    public void addRecommendMission(MissionRecommendAddDTO missionRecommendAddDTO) {

        MissionIcon missionIcon = missionIconRepository.findById(missionRecommendAddDTO.getMissionIconId()).get();

        Mission mission = new Mission(missionRecommendAddDTO, missionIcon);

        missionRepository.save(mission);
    }

    // 미션 리스트
    @Transactional(readOnly = true)
    public List<MissionReadDTO> getMissionList(Long routineId) {

//		Type type = new TypeToken<List<MissionReadDTO>>() {}.getType();
//        TypeMap<Mission, MissionReadDTO> typeMap = modelMapper.typeMap(Mission.class, MissionReadDTO.class);
//        typeMap.addMapping(Mission::getId, MissionReadDTO::setMissionId);
//
//		return modelMapper.map(missionRepository.findAllByRoutineId(routineId), type );
		return missionRepository.findAllByRoutineId(routineId)
				.stream().map(mission -> new MissionReadDTO(mission))
				.collect(Collectors.toList());
    }

    // 미션 추가
    public void addMission(MissionAddDTO missionAddDTO) {

        Routine routine = routineRepository.findById(missionAddDTO.getRoutineId()).get();
        MissionIcon missionIcon = missionIconRepository.findById(missionAddDTO.getMissionIconId()).get();

        Mission mission = new Mission(missionAddDTO, routine, missionIcon);
		missionRepository.save(mission);

		routineRepository.save(routine.updateTotaTime(missionAddDTO.getRunTime()));

    }

	public MissionReadDTO getMission(Long missionId) {

        Mission mission = missionRepository.findById(missionId).get();

        MissionReadDTO missionReadDTO = modelMapper.map(mission, MissionReadDTO.class);
        missionReadDTO.setIconPath(mission.getMissionIcon().getIconCategory().getIconPath());

        return missionReadDTO;
    }

    public void editRecommendMission(MissionRecommendEditDTO missionRecommendEditDTO) {

        Mission mission = missionRepository.findById(missionRecommendEditDTO.getMissionId()).orElse(null);
        MissionIcon missionIcon = missionIconRepository.findById(missionRecommendEditDTO.getMissionIconId()).orElse(null);

        mission.edit(missionRecommendEditDTO, missionIcon);
    }

	public void deleteMission(Long missionId) {
        missionRepository.deleteById(missionId);
	}
}
