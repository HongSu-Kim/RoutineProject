package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionDTO {

    private int missionNum;
    private int routineNum;
    private int iconNum;
    private String missionName;
    private String runTime;
    private String missionContent;

    private String iconFileName;

}
