package com.soo.routine.dto.mission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionDTO {

    private Long missionId;
    private Long routineId;
    private Long iconId;
    private String missionName;
    private int orderNum;
    private String runTime;
    private String missionContent;

    private String iconFileName;

}
