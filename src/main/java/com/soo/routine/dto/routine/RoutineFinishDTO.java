package com.soo.routine.dto.routine;

import com.soo.routine.dto.mission.MissionReadDTO;
import com.soo.routine.entity.routine.Routine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class RoutineFinishDTO {

	private Long routineId;
	private String[] remainingTime;
	private String[] elapsedTime;

	private String routineName;
	private LocalTime totalTime;

	private LocalTime startTime;
	private LocalTime finalTime;

	private List<MissionReadDTO> missionList;

	public RoutineFinishDTO(Routine routine) {
		routineId = routine.getId();
		routineName = routine.getRoutineName();
		totalTime = routine.getTotalTime();
		startTime = routine.getRoutineSetList().get(0).getStartTime();
		missionList = routine.getMissionList().stream()
				.map(MissionReadDTO::new)
				.collect(Collectors.toList());
	}
}
