package com.soo.routine.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoutineDTO {

    private int routineNum;
    private int idx;
    private String routineName;
    private boolean active;
    private String totalTime;

    private List<Integer> week;
    private List<String> timeList;

}
