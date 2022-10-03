package com.soo.routine.dto.routine;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RoutineRecommendEditDTO {

    private Long routineId;

    private Long memberId;
    private String memberNickname;
    
    @NotBlank(message = "루틴명을 입력해주세요")
    private String routineName;

    private boolean routineActive;
    private String totalTime;

    // mission
    private Long[] missionIconId;
    private String[] missionName;
    private String[] runtime;
    private String[] missionContent;

}
