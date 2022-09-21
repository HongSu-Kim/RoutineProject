package com.soo.routine.repository;

import com.soo.routine.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findAllByRoutineId(Long routineId);
    List<Mission> findAllByRoutineMemberLevel(String admin);
}
