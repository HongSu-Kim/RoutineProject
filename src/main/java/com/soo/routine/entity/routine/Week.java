package com.soo.routine.entity.routine;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Week {
    SUN("WEEK_SUN", 0, "일", false),
	MON("WEEK_MON", 1, "월", true),
	TUE("WEEK_TUE", 2, "화", true),
	WED("WEEK_WED", 3, "수", true),
	THU("WEEK_THU", 4, "목", true),
	FRI("WEEK_FRI", 5, "금", true),
	SAT("WEEK_SAT", 6, "토", false)
	;

	private String key;
	private int value;
	private String button;
	private boolean bool;
}
