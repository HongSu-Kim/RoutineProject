package com.soo.routine.dto.mission;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MissionAddRecommendDTO {

    @NotNull(message = "아이콘을 선택해주세요")
    private Long iconId;

    @NotBlank(message = "미션명을 입력해주세요")
    private String missionName;

    @NotNull(message = "시간을 입력해주세요")
    @Pattern(regexp = "^\\d\\d\\d\\d\\d\\d$", message = "시간형식을 맞게 입력해주세요")
    private String runTime;

    private String missionContent;
}
