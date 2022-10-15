package com.soo.routine.entity.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER"),
    WITHDRAW("ROLE_WITHDRAW");

    private String value;

}
