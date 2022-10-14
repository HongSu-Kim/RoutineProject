package com.soo.routine.dto.routine;

import com.soo.routine.entity.routine.Routine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class RoutineUpdateDTO {

    private Long routineId;
    private Long memberId;
    
    @NotBlank(message = "루틴명을 입력해주세요")
    private String routineName;

    private boolean routineActive;
    private String totalTime;

    // routine set
    @NotNull(message = "시작시간을 설정해주세요")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$", message = "시간형식에 맞게 입력해주세요(hh:mm)")
    private String startTime;
    private boolean[] weekActive;

    // mission
    private Long[] iconId;
    private String[] missionName;
    private String[] runTime;
    private String[] missionContent;

	public RoutineUpdateDTO(Routine routine) {
		routineId = routine.getId();
		memberId = routine.getMember().getId();
		routineName = routine.getRoutineName();
		routineActive = routine.isRoutineActive();
		totalTime = routine.getTotalTime().toString();
		startTime = routine.getRoutineSetList().get(0).getStartTime().toString();
	}
}
