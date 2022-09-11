package com.soo.routine.repository;

import com.soo.routine.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    List<Board> findAllByCategoryAndMemberNum(String category, String memberNum);
    List<Board> findAllByCategory(String category);

}
