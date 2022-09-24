package com.soo.routine.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class MissionIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_icon_id")
    private Long id;//아이콘번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iconCategory_id")
    private IconCategory iconCategory;//카테고리번호 FK

    private String iconFileName;//아이콘파일명

    @OneToMany(mappedBy = "missionIcon")
    private List<Mission> missionList;

//    public MissionIcon add() {
//        this.IconCategory = iconFile.get
//
//    }

}
