package com.soo.routine.entity.mission;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class IconCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "icon_category_id")
    private Long id;//카테고리번호 PK

    private String categoryName;//카테고리이름
    private String iconPath;//아이콘경로
    private Boolean pay;//유료여부


    @OneToMany(mappedBy = "iconCategory", cascade = CascadeType.REMOVE)
    private List<MissionIcon> missionIconList;

}
