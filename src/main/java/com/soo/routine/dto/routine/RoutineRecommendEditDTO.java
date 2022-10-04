package com.soo.routine.dto.routine;

import com.soo.routine.entity.mission.Mission;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
    private List<Mission> missionList;
    private Long[] missionId;
    private Long[] missionIconId;
    private String[] missionName;
    private String[] runTime;
    private String[] missionContent;

}
