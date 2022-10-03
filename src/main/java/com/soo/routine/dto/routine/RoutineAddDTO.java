package com.soo.routine.dto.routine;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RoutineAddDTO {

    private Long memberId;
    
    @NotBlank(message = "루틴명을 입력해주세요")
    private String routineName;

    private boolean routineActive = true;
    private String totalTime = "00:00:00";

    // routine set
    @NotNull(message = "시작시간을 설정해주세요")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$", message = "시간형식에 맞게 입력해주세요(hh:mm)")
    private String startTime;
    private boolean[] weekActive;

    // mission
    private Long[] iconId;
    private String[] missionName;
    private String[] runtime;
    private String[] missionContent;

}
