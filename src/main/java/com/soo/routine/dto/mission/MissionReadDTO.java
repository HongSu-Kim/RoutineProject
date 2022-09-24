package com.soo.routine.dto.mission;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class MissionReadDTO {

    private Long missionId;
    private String routineId;
    private String iconFileName;
    private String missionName;
    private int missionOrder;
    private LocalTime runTime;
    private String missionContent;

}
