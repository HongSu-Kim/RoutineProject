package com.soo.routine.repository;

import com.soo.routine.entity.MissionIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionIconRepository extends JpaRepository<MissionIcon, Integer> {
    
}
