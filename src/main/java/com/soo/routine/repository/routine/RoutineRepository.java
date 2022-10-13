package com.soo.routine.repository.routine;

import com.soo.routine.entity.member.Role;
import com.soo.routine.entity.routine.Routine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

	Optional<Routine> findById(Long routineId);
    List<Routine> findAllByMemberId(Long memberId);
    Page<Routine> findAllByMemberRole(Role role, Pageable pageable);
}
