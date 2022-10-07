package com.soo.routine.repository.board;

import com.soo.routine.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @EntityGraph(attributePaths = {"member", "reply"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Board> findAllByCategoryAndMemberId(String category, Long memberId, Pageable pageable);

    @EntityGraph(attributePaths = {"member", "reply"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Board> findAllByCategory(String category, Pageable pageable);

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Board> findById(Long boardId);
}
