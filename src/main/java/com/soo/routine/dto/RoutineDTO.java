package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineDTO {

    private int routineNum;
    private int idx;
    private String routineName;
    private boolean active;
    private String totalTime;

}
