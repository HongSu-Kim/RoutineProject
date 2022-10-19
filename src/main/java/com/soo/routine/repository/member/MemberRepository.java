package com.soo.routine.repository.member;

import com.soo.routine.entity.member.Role;
import com.soo.routine.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Page<Member> findByRole(Role role, Pageable pageable);

}
