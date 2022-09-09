package com.soo.routine.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class IconCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryNum;//카테고리번호 PK
    private String categoryName;//카테고리이름
    private Boolean pay;//유료여부


    @OneToMany(mappedBy = "iconCategory", cascade = CascadeType.REMOVE)
    private List<MissionIcon> missionIconList;

}
