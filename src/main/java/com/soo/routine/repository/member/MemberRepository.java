package com.soo.routine.repository.member;

import com.soo.routine.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);

//    Optional<Member> findOne(String email);

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

    public List<Member> findByLevel(String level);

}
