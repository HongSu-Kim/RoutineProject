package com.soo.routine.entity;

import com.soo.routine.dto.mission.MissionAddDTO;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Integer id;//미션번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;//루틴번호 FK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "missionIcon_id")
    private MissionIcon missionIcon;//미션아이콘번호 FK

    private String missionName;//미션이름
    private int missionOrder;//미션순서
    private LocalTime runTime;//소요시간
    private String missionContent;//내용


    public Mission addRecommend(MissionAddDTO missionAddDTO, Routine routine, MissionIcon missionIcon) {
        this.routine = routine;
        this.missionIcon = missionIcon;
        this.missionName = missionAddDTO.getMissionName();
        this.missionOrder = missionAddDTO.getOrderNum();
        this.runTime = missionAddDTO.getRunTime();
        this.missionContent = missionAddDTO.getMissionContent();
        return this;
    }

}
