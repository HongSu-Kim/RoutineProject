package com.soo.routine.entity.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Level {
    ADMIN("LEVEL_ADMIN"),
    MEMBER("LEVEL_MEMBER");

    private String value;
}
