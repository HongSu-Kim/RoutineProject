package com.soo.routine.dto.routine;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoutineDTO {

    private Long routineId;
    private Long memberId;
    private String routineName;
    private boolean active;
    private String totalTime;

    private List<String> week;
    private List<String> timeList;

}
