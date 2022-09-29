package com.soo.routine.dto.mission;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
public class MissionAddDTO {

    private Long routineId;

    @NotNull(message = "아이콘을 정해주세요")
    private Long iconId;

    @NotBlank(message = "미션명을 입력해주세요")
    private String missionName;

    private int orderNum;

    @NotNull(message = "시간을 설정해주세요")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$", message = "시간형식에 맞게 입력해주세요(hh:mm:ss)")
    private String runTime;

    private String missionContent;

}
