package com.soo.routine.repository.mission;

import com.soo.routine.entity.mission.MissionIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionIconRepository extends JpaRepository<MissionIcon, Long> {
    
}
