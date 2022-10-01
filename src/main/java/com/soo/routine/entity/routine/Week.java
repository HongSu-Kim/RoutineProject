package com.soo.routine.entity.routine;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Week {
	SUNDAY("WEEK_SUN", 0, "일", false),
	MONDAY("WEEK_MON", 1, "월", true),
	TUESDAY("WEEK_TUE", 2, "화", true),
	WEDNESDAY("WEEK_WED", 3, "수", true),
	THURSDAY("WEEK_THU", 4, "목", true),
	FRIDAY("WEEK_FRI", 5, "금", true),
	SATURDAY("WEEK_SAT", 6, "토", false)
	;

	private String key;
	private int value;
	private String button;
	private boolean bool;
}
