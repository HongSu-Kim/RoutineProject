package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineDTO {

    private int routineNum;
    private int idx;
    private String routineName;
    private boolean activeornot;
    private boolean complteornot;
    private String totalTime;

    private int week;
    private String startTime;

}
