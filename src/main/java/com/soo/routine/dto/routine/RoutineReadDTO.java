package com.soo.routine.dto.routine;

import com.soo.routine.entity.Mission;
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
    private boolean active;
    private LocalTime totalTime;

    private List<String> weekList;
    private List<LocalTime> startTimeList;

    private List<Mission> missionList;

}
