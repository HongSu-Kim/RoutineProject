package com.soo.routine.dto.mission;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
public class MissionAddDTO {

    @NotNull(message = "루틴을 정해주세요")
    private int routineId;

    @NotNull(message = "아이콘을 정해주세요")
    private int iconId;

    @NotBlank(message = "미션명을 입력해주세요")
    private String missionName;

    private int orderNum;

    @NotNull(message = "시간을 설정해주세요")
    private LocalTime runTime;

    private String missionContent;

}
