package com.soo.routine.service.mission;

import com.soo.routine.dto.mission.MissionIconDTO;
import com.soo.routine.repository.mission.MissionIconRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MissionIconService {

	private final MissionIconRepository missionIconRepository;

	public List<MissionIconDTO> getIconList() {
		return missionIconRepository.findAll()
				.stream().map(MissionIconDTO::new)
				.collect(Collectors.toList());
	}
}
