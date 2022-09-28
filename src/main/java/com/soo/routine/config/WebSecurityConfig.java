package com.soo.routine.config;

import com.soo.routine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

//    @Bean
//    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 로그인 시 필요한 정보를 가져옴
//        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        String pwd = passwordEncoder().encode("111");

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("pwd")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("pwd")
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { //인증을 무시할 경로 설정
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // http 관련 인증 설정
        http
            .authorizeRequests() // 페이지 접근에 대한 인증 설정
                .antMatchers("/admin/**").hasRole("ADMIN") // admin만 접근 가능
                .antMatchers("/").hasRole("MEMBER") // user(member,admin)만 접근 가능
                .antMatchers("/**").permitAll() // 모든 user(non-member,member,admin) 접근 가능
//                .antMatchers("/routine/**", "/profile/**", "/admin/**").permitAll() // 모든 user(non-member,member,admin) 접근 가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
            .and()
                .formLogin() // 로그인에 관한 설정
                .loginPage("/startRoutine") // 로그인 안 된 경우 페이지
                .defaultSuccessUrl("/routine") // 로그인 시 리다이렉트 페이지
            .and()
                .logout() // 로그아웃에 관한 설정
                .logoutSuccessUrl("/startRoutine") // 로그아웃 시 리다이렉트 주소
                .invalidateHttpSession(true); // 세션 날리기

        return http.build();
    }

}