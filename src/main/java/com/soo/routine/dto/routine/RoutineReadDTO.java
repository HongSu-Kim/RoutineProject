package com.soo.routine.dto.routine;

import com.soo.routine.entity.mission.Mission;
import com.soo.routine.entity.routine.RoutineSet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RoutineReadDTO {

    private Long routineId;
    private Long memberId;
    private String routineName;
    private boolean routineActive;
    private LocalTime totalTime;

    private RoutineSet routineSet;
    private List<RoutineSet> routineSetList;

    private List<Mission> missionList;


    private LocalTime finalTime;

}
