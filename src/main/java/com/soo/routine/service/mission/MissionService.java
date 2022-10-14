package com.soo.routine.service.mission;

import com.soo.routine.dto.mission.MissionAddDTO;
import com.soo.routine.dto.mission.MissionReadDTO;
import com.soo.routine.dto.mission.MissionRecommendAddDTO;
import com.soo.routine.dto.mission.MissionRecommendEditDTO;
import com.soo.routine.entity.mission.Mission;
import com.soo.routine.entity.mission.MissionIcon;
import com.soo.routine.entity.routine.Routine;
import com.soo.routine.repository.mission.MissionIconRepository;
import com.soo.routine.repository.mission.MissionRepository;
import com.soo.routine.repository.routine.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // 추천 미션 추가
    public void addRecommendMission(MissionRecommendAddDTO missionRecommendAddDTO) {

        MissionIcon missionIcon = missionIconRepository.findById(missionRecommendAddDTO.getMissionIconId()).get();

        Mission mission = new Mission(missionRecommendAddDTO, missionIcon);

        missionRepository.save(mission);
    }

    // 미션 리스트
    @Transactional(readOnly = true)
    public List<MissionReadDTO> getMissionList(Long routineId) {
		return missionRepository.findAllByRoutineId(routineId)
				.stream().map(MissionReadDTO::new)
				.collect(Collectors.toList());
    }

    // 미션 추가
    public void addMission(MissionAddDTO missionAddDTO) {

        Routine routine = routineRepository.findById(missionAddDTO.getRoutineId()).get();
        MissionIcon missionIcon = missionIconRepository.findById(missionAddDTO.getMissionIconId()).get();

        Mission mission = new Mission(missionAddDTO, routine, missionIcon);
		missionRepository.save(mission);

		routineRepository.save(routine.updateTotalTime(missionAddDTO.getRunTime()));
    }

	public MissionReadDTO getMission(Long missionId) {
		return missionRepository.findById(missionId).map(MissionReadDTO::new).orElse(null);
    }

    public void editRecommendMission(MissionRecommendEditDTO missionRecommendEditDTO) {

		Mission mission = missionRepository.findById(missionRecommendEditDTO.getMissionId()).orElse(null);
        MissionIcon missionIcon = missionIconRepository.findById(missionRecommendEditDTO.getMissionIconId()).orElse(null);

		mission.edit(missionIcon, missionRecommendEditDTO.getMissionName(), missionRecommendEditDTO.getRunTime(), missionRecommendEditDTO.getMissionContent());
    }

	public void deleteMission(Long missionId) {
        missionRepository.deleteById(missionId);
	}

	public Page<MissionReadDTO> getMissionPage(Pageable pageable) {
		return missionRepository.findAllByRoutineId(null, pageable).map(MissionReadDTO::new);
	}
}
