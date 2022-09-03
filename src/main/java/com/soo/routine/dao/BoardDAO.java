package com.soo.routine.dao;

import com.soo.routine.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardDAO {

    private SqlSessionTemplate sessionTemplate;

    public List<BoardDTO> getList(String categoryName) {
        return sessionTemplate.selectList("boardMapper.readList", categoryName);
    }





}
