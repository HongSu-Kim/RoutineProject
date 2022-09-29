package com.soo.routine.repository.routine;

import com.soo.routine.entity.routine.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

    List<Routine> findAllByMemberId(Long memberId);
    List<Routine> findAllByMemberLevel(String admin);
}
