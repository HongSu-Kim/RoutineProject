package com.soo.routine.dto.routine;

import com.soo.routine.entity.Mission;
import com.soo.routine.entity.RoutineSet;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RoutineUpdateDTO {

    private int routineId;
    private int memberId;
    
    @NotBlank(message = "루틴명을 입력해주세요")
    private String routineName;

    private boolean active;
    private LocalTime totalTime;

    private Integer[] missionId;
    private String[] week;
    private LocalTime[] startTime;

}
