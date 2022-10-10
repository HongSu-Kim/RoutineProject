package com.soo.routine.dto.mission;

import com.soo.routine.entity.mission.IconCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class MissionReadDTO {

    private Long missionId;
    private String routineId;

    private Long missionIconId;
    private String iconFileName;
    private String iconPath;
    private IconCategory iconCategory;

    private String missionName;
    private int missionOrder;
    private LocalTime runTime;
    private String missionContent;


}
