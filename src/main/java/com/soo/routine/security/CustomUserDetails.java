package com.soo.routine.security;

import com.soo.routine.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 로그인 성공 시, Spring Security의 Session 저장소에 CustomUserDetails를 저장한다

@ToString
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Member member;

    // 유저 권한 목록
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> "ROLE_" + member.getRole());
        return collection;
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public String getPassword() {
        return member.getPwd();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료X
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠김X
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호 만료X
    }

    @Override
    public boolean isEnabled() {
        return true; // 사용자 활성화 만료X
    }

}
