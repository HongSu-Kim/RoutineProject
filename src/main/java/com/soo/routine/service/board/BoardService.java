package com.soo.routine.service.board;

import com.soo.routine.dto.board.*;
import com.soo.routine.entity.board.Board;
import com.soo.routine.entity.member.Member;
import com.soo.routine.repository.board.BoardRepository;
import com.soo.routine.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 리스트
    @Transactional(readOnly = true)
    public Page<BoardListDTO> getBoardList(String category, Pageable pageable) {

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by(sorts));

        if (category == null)
			return boardRepository.findAll(pageable).map(BoardListDTO::new);
        else
			return boardRepository.findAllByCategory(category, pageable).map(BoardListDTO::new);

    }

    // QnA 리스트
    @Transactional(readOnly = true)
    public Page<BoardQnaListDTO> getQnaList(String category, Long memberId, Pageable pageable) {

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by(sorts));

        if (memberId == null || memberId.toString().equals("")) {
            return boardRepository.findAllByCategory(category, pageable).map(BoardQnaListDTO::new);
        } else {
            return boardRepository.findAllByCategoryAndMemberId(category, memberId, pageable).map(BoardQnaListDTO::new);
        }
    }

    // 게시글 작성
    public void writeBoard(BoardWriteDTO boardWriteDTO) {

        Member member = memberRepository.findById(boardWriteDTO.getMemberId()).orElse(null);

		Board board = new Board(boardWriteDTO, member);

        boardRepository.save(board);
    }

    // 게시글 한개
    public BoardReadDTO getBoard(Long boardId) {
        return boardRepository.findById(boardId)
				.map(board -> new BoardReadDTO(board.updateHits()))
				.orElse(null);
    }

    // 게시글 수정
    public void editBoard(BoardEditDTO boardEditDTO) {

        Board board = boardRepository.findById(boardEditDTO.getBoardId()).orElse(null);

        if (board == null) {
            return;
        }

        board.edit(boardEditDTO);

//        boardRepository.save(board);
    }
}
