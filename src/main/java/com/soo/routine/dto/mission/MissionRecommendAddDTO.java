package com.soo.routine.dto.mission;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MissionRecommendAddDTO {

    @NotNull(message = "아이콘을 선택해주세요")
    private Long missionIconId;

    @NotBlank(message = "미션명을 입력해주세요")
    private String missionName;

    @NotNull(message = "시간을 입력해주세요")
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$", message = "시간형식에 맞게 입력해주세요(hh:mm)")
    private String runTime;

    private String missionContent;
}
