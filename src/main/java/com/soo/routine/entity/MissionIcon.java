package com.soo.routine.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MissionIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iconNum;//아이콘번호 PK

    @ManyToOne
    @JoinColumn(name = "iconCategory_num")
    private IconCategory iconCategory;//카테고리번호 FK

    private String iconFileName;//아이콘파일명


    @OneToOne(mappedBy = "missionIcon")
    private Mission mission;

}
