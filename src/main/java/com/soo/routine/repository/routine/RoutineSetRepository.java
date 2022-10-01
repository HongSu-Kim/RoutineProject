package com.soo.routine.repository.routine;

import com.soo.routine.entity.routine.RoutineSet;
import com.soo.routine.entity.routine.RoutineSetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineSetRepository extends JpaRepository<RoutineSet, RoutineSetId> {

}
