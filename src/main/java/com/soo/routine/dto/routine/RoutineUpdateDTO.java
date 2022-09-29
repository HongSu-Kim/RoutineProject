package com.soo.routine.dto.routine;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Getter
@Setter
public class RoutineUpdateDTO {

    private Long routineId;
    private Long memberId;
    
    @NotBlank(message = "루틴명을 입력해주세요")
    private String routineName;

    private boolean routineActive;
    private LocalTime totalTime;

    private Long[] missionId;
    private String[] week;
    private LocalTime[] startTime;

}
