package com.soo.routine.dto.mission;

import com.soo.routine.entity.mission.IconCategory;
import com.soo.routine.entity.mission.Mission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class MissionReadDTO {

    private Long missionId;
    private Long routineId;

    private Long missionIconId;
    private String iconFileName;
    private String iconPath;
    private IconCategory iconCategory;

    private String missionName;
    private int missionOrder;
    private LocalTime runTime;
    private String missionContent;

	public MissionReadDTO(Mission mission) {
		missionId = mission.getId();
		routineId = mission.getRoutine().getId();
		missionName = mission.getMissionName();
		missionOrder = mission.getMissionOrder();
		runTime = mission.getRunTime();
		missionContent = mission.getMissionContent();

		missionIconId = mission.getMissionIcon().getId();
		iconFileName = mission.getMissionIcon().getIconFileName();
		iconCategory = mission.getMissionIcon().getIconCategory();

		iconPath = mission.getMissionIcon().getIconCategory().getIconPath();
	}

}
