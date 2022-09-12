package com.soo.routine.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Integer id;//루틴번호 PK

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    private String routineName;//루틴명
    private boolean active;//활성화
    private LocalTime totalTime;//전체시간


    @OneToMany(mappedBy = "routine", cascade = CascadeType.REMOVE)
    private List<Mission> missionList;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.REMOVE)
    private List<RoutineSet> routineSetList;

}
