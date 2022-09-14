package com.soo.routine.repository;

import com.soo.routine.entity.Member;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(String email);//이메일 중복확인
    Optional<Member> findByNickname(String nickname);//닉네임 중복확인

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

    List<Member> findAll();

}
