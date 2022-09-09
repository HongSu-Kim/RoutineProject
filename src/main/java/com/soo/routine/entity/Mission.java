package com.soo.routine.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer missionNum;//미션번호 PK

    @ManyToOne
    @JoinColumn(name = "routine_num")
    private Routine routine;//루틴번호 FK

    @OneToOne
    @JoinColumn(name = "missionIcon_num")
    private MissionIcon missionIcon;//미션아이콘번호 FK

    private String missionName;//미션이름
    private String missionOrder;//미션순서
    private LocalTime runTime;//소요시간
    private String missionContent;//내용

}