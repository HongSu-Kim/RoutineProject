package com.soo.routine.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 세션 관련 중복 코드 제거

@Target(ElementType.PARAMETER) // Parameter로 선언한 객체만 사용한다
@Retention(RetentionPolicy.RUNTIME) // Runtime까지 유지된다
public @interface LoginUser {

}