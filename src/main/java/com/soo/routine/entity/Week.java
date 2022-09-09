package com.soo.routine.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Week {

    SUNDAY("WEEK_SUNDAY", "일요일"),
    MONDAY("WEEK_MONDAY", "월요일"),
    TUESDAY("WEEK_TUESDAY", "화요일"),
    WEDNESDAY("WEEK_WEDNESDAY", "수요일"),
    THURSDAY("WEEK_THURSDAY", "목요일"),
    FRIDAY("WEEK_FRIDAY", "금요일"),
    SATURDAY("WEEK_SATURDAY", "토요일");

    private final String key;
    private final String value;

}
