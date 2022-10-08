package com.soo.routine.dto.routine;

import com.soo.routine.entity.mission.Mission;
import com.soo.routine.entity.routine.RoutineSet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RoutineDTO {

    private Long routineId; // 루틴 ID
    private Long memberId; // 회원 ID

    private String routineName; // 루틴 이름
    private boolean routineActive; // 루틴 활성화
    private LocalTime totalTime; // 총 시간

    private RoutineSet routineSet;
    private List<RoutineSet> routineSetList;

    private List<Mission> missionList;


    private LocalTime finalTime;

}
