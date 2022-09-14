package com.soo.routine.repository;

import com.soo.routine.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Integer> {

    List<Routine> findAllByMemberId(int memberId);
}
