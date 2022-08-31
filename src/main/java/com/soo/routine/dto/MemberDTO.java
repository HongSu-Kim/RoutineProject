package com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    private int idx;
    private String id;
    private String pwd;
    private String nickname;
    private String gender;
    private String birth;
    private String grade;
    private String joinDate;
    private String lastDate;

}
