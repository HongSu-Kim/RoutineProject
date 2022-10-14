package com.soo.routine.dto.mission;

import com.soo.routine.entity.mission.MissionIcon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MissionIconDTO {

    private Long missionIconId;
    private Long iconCategoryId;
    private String iconFileName;

	public MissionIconDTO(MissionIcon missionIcon) {
		missionIconId = missionIcon.getId();
		iconCategoryId = missionIcon.getIconCategory().getId();
		iconFileName = missionIcon.getIconFileName();
	}

}
