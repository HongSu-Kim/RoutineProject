package com.soo.routine.mapper;

import com.soo.routine.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    public int maxNum();
    public List<Member> getList(String grade);
    public void createData();
    public Member getData();
    public String getNickname(int memberNum);



}
