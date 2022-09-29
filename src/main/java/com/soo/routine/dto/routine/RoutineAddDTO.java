package com.soo.routine.dto.routine;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Getter
@Setter
public class RoutineAddDTO {

    private Long memberId;
    
    @NotBlank(message = "루틴명을 입력해주세요")
    private String routineName;

    private boolean routineActive = true;
    private String totalTime = "00:00:00";
    private String week;

//    @NotNull(message = "시간을 설정해주세요")
    private LocalTime startTime;

    // mission
    private Long[] iconId;
    private String[] missionName;
    private String[] runtime;
    private String[] missionContent;

//    private List<RoutineSet> routineSetList;
//    private List<Mission> missionList;

}
