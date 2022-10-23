package com.soo.routine.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

// 로그인 실패 시 에러 메세지
@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String errorMessage; // 기본 에러 메세지

        if (exception instanceof UsernameNotFoundException) {
            errorMessage = "이메일이 존재하지 않습니다.";
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "이메일 또는 비밀번호를 정확하게 입력하세요.";
        } else if (exception instanceof DisabledException) { // 로그인 5번 실패 시
            errorMessage = "계정이 비활성화되었습니다. 조금 뒤에 다시 로그인하세요.";
        } else {
            errorMessage = "알 수 없는 이유로 인해 로그인을 할 수 없습니다.";
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
