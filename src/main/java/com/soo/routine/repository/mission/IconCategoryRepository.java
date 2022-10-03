package com.soo.routine.repository.mission;

import com.soo.routine.entity.mission.IconCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IconCategoryRepository extends JpaRepository<IconCategory, Long> {

}
