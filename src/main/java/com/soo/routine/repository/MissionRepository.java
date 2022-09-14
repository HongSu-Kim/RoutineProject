package com.soo.routine.repository;

import com.soo.routine.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {

    List<Mission> findAllByRoutineId(int routineId);
}
