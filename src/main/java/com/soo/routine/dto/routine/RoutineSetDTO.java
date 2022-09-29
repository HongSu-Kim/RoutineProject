package com.soo.routine.dto.routine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineSetDTO {

    private Long routineId;
    private Week week;
    private boolean weekActive;
    private String startTime;

}
