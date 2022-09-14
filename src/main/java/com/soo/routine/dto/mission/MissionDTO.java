package com.soo.routine.dto.mission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionDTO {

    private int missionId;
    private int routineId;
    private int iconId;
    private String missionName;
    private int orderNum;
    private String runTime;
    private String missionContent;

    private String iconFileName;

}
