package com.soo.routine.dto.mission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionReadDTO {

    private int missionId;
    private String routineId;
    private String iconFileName;
    private String missionName;
    private int orderNum;
    private String runTime;
    private String missionContent;

}
