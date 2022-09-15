package com.soo.routine.dto.routine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineAddDTO {

    private int memberId;
    private String routineName;
    private boolean active;
    private String totalTime;

    private Integer[] missionId;
    private String[] week;
    private String[] startTime;

}
