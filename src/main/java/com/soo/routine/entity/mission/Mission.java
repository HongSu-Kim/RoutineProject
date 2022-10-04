package com.soo.routine.entity.mission;

import com.soo.routine.dto.mission.MissionAddDTO;
import com.soo.routine.dto.mission.MissionRecommendAddDTO;
import com.soo.routine.dto.mission.MissionRecommendEditDTO;
import com.soo.routine.entity.routine.Routine;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;//미션번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;//루틴번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "missionIcon_id")
    private MissionIcon missionIcon;//미션아이콘번호 FK

    private String missionName;//미션이름
    private int missionOrder;//미션순서
    private LocalTime runTime;//소요시간
    private String missionContent;//내용

    // 추천 미션 추가
    public Mission addRecommend(MissionRecommendAddDTO missionRecommendAddDTO, MissionIcon missionIcon) {
        this.missionIcon = missionIcon;
        this.missionName = missionRecommendAddDTO.getMissionName();
        this.runTime = LocalTime.parse(missionRecommendAddDTO.getRunTime(), DateTimeFormatter.ISO_LOCAL_TIME);
        this.missionContent = missionRecommendAddDTO.getMissionContent();
        return this;
    }
    public Mission addRecommend(Routine routine, MissionIcon missionIcon, String missionName, String runTime, String missionContent) {
        this.routine = routine;
        this.missionIcon = missionIcon;
        this.missionName = missionName;
        this.runTime = LocalTime.parse(runTime, DateTimeFormatter.ISO_LOCAL_TIME);
        this.missionContent = missionContent;
        return this;
    }
    
    // 미션 추가
    public Mission add(MissionAddDTO missionAddDTO, Routine routine, MissionIcon missionIcon) {
        this.routine = routine;
        this.missionIcon = missionIcon;
        this.missionName = missionAddDTO.getMissionName();
        this.runTime = LocalTime.parse(missionAddDTO.getRunTime(), DateTimeFormatter.ISO_LOCAL_TIME);
        this.missionContent = missionAddDTO.getMissionContent();
        return this;
    }

    // 추천 미션수정
    public void edit(MissionRecommendEditDTO missionRecommendEditDTO, MissionIcon missionIcon) {
        this.missionIcon = missionIcon;
        this.missionName = missionRecommendEditDTO.getMissionName();
        this.runTime = LocalTime.parse(missionRecommendEditDTO.getRunTime());
        this.missionContent = missionRecommendEditDTO.getMissionContent();
    }

    // 루틴 내 미션 수정
    public void edit(MissionIcon missionIcon, String missionName, String runTime, String missionContent) {
        this.missionIcon = missionIcon;
        this.missionName = missionName;
        this.runTime = LocalTime.parse(runTime);
        this.missionContent = missionContent;
    }
}
