package com.soo.routine.repository;

import com.soo.routine.entity.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @EntityGraph(attributePaths = {"member", "reply"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Board> findAllByCategoryAndMemberId(String category, Long memberId);

    @EntityGraph(attributePaths = {"member", "reply"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Board> findAllByCategory(String category);

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Board> findById(Long boardId);
}
