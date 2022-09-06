package com.soo.routine.mapper;

import com.soo.routine.domain.Reply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {

    void createData(Reply reply);


}
