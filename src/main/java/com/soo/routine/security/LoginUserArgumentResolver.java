package com.soo.routine.security;

import com.soo.routine.dto.member.SessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

// @LoginUser를 사용하기 위한 클래스

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isSessionClass = SessionDTO.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isSessionClass; // @LoginUser이고 Class Type이 SessionDTO이면, true를 반환한다
    }

    // Parameter에 전달할 객체 생성
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        System.out.print("ㅠㅠㅠ");
        return httpSession.getAttribute("member");
    }
}
