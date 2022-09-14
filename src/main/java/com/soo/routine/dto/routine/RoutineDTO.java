package com.soo.routine.dto.routine;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoutineDTO {

    private int routineId;
    private int memberId;
    private String routineName;
    private boolean active;
    private String totalTime;

    private List<Integer> week;
    private List<String> timeList;

}
