package com.soo.routine.dto.mission;

import com.soo.routine.entity.mission.Mission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class MissionStartDTO {

	private Long missionId;
	private Long nextMissionId;
	private Long routineId;

	private String iconFileName;
	private String iconPath;

	private String missionName;
//	private int missionOrder;
	private LocalTime runTime;

	public MissionStartDTO(Mission mission) {
		missionId = mission.getId();
		routineId = mission.getRoutine().getId();
		iconFileName = mission.getMissionIcon().getIconFileName();
		iconPath = mission.getMissionIcon().getIconCategory().getIconPath();
		missionName = mission.getMissionName();
		runTime = mission.getRunTime();
	}
}
