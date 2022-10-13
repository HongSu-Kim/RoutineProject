package com.soo.routine.repository.mission;

import com.soo.routine.entity.mission.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @EntityGraph(attributePaths = {"missionIcon"}, type = EntityGraph.EntityGraphType.LOAD)
	List<Mission> findAllByRoutineId(Long routineId);
	Page<Mission> findAllByRoutineId(Long routineId, Pageable pageable);
}
