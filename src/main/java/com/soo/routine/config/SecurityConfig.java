package com.soo.routine.config;

import com.soo.routine.security.CustomFailureHandler;
import com.soo.routine.service.member.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소 접근 시, 권한 및 인증을 미리 체크
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomFailureHandler customFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/plugins/**", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // http 관련 인증 설정
        http
            .csrf().disable()
            .authorizeRequests() // 페이지 접근에 대한 인증 설정
                .antMatchers("/admin/**").hasRole("ADMIN") // admin만 접근 가능
                .antMatchers("/", "/startRoutine", "/join", "/login", "/pwd-find").access("!hasRole('MEMBER') and !hasRole('ADMIN')") // 비회원만 접근 가능
                .anyRequest().hasAnyRole("MEMBER", "ADMIN") // 나머지 요청들은 user(member,admin)만 접근 가능
            .and()
                .formLogin() // 로그인에 관한 설정
                    .loginPage("/login") // 로그인 페이지
                    .usernameParameter("email")
                    .passwordParameter("pwd")
                    .loginProcessingUrl("/login")
                    .failureHandler(customFailureHandler) // 로그인 실패 시
                    .defaultSuccessUrl("/mypage") // 로그인 시 리다이렉트 페이지
            .and()
                .logout() // 로그아웃에 관한 설정
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login") // 로그아웃 시 리다이렉트 주소
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID") // 세션 삭제
            .and()
                .exceptionHandling() // 예외 처리
                .accessDeniedPage("/deny");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

}
