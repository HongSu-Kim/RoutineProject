package com.soo.routine.dto.mission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionReadDTO {

    private Long missionId;
    private String routineId;
    private String iconFileName;
    private String missionName;
    private int missionOrder;
    private String runTime;
    private String missionContent;

}
