package com.soo.routine.dto.routine;

import com.soo.routine.dto.mission.MissionReadDTO;
import com.soo.routine.entity.routine.Routine;
import com.soo.routine.entity.routine.Week;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class RoutineDTO {

    private Long routineId; // 루틴 ID
    private Long memberId; // 회원 ID

    private String routineName; // 루틴 이름
    private boolean routineActive; // 루틴 활성화
    private LocalTime totalTime; // 총 시간
    private LocalTime finalTime; // 종료 시간

    private Week week; // 요일
    private boolean weekActive; // 요일 활성화
    private LocalTime startTime; // 시작 시간

//    private RoutineSet routineSet;
//    private List<RoutineSet> routineSetList;

    private String weekList;

    private List<MissionReadDTO> missionList;

	public RoutineDTO(Routine routine) {
		routineId = routine.getId();
		routineName = routine.getRoutineName();
		routineActive = routine.isRoutineActive();
		totalTime = routine.getTotalTime();

		missionList = routine.getMissionList().stream()
				.map(MissionReadDTO::new)
				.collect(Collectors.toList());
	}

}
