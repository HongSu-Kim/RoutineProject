package com.soo.routine.entity.routine;

import com.soo.routine.dto.routine.RoutineAddDTO;
import com.soo.routine.dto.routine.RoutineRecommendAddDTO;
import com.soo.routine.dto.routine.RoutineUpdateDTO;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.mission.Mission;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;//루틴번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    private String routineName;//루틴명
    private boolean routineActive;//루틴활성화
    private LocalTime totalTime;//전체시간


    @OneToMany(mappedBy = "routine", cascade = CascadeType.REMOVE)
    private List<Mission> missionList;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.REMOVE)
    private List<RoutineSet> routineSetList;

    // 루틴 추가
    public Routine addRecommendRoutine(RoutineRecommendAddDTO routineRecommendAddDTO, Member member) {
        this.member = member;
        this.routineName = routineRecommendAddDTO.getRoutineName();
        this.routineActive = routineRecommendAddDTO.isRoutineActive();
        this.totalTime = LocalTime.parse(routineRecommendAddDTO.getTotalTime(), DateTimeFormatter.ISO_LOCAL_TIME);
        return this;
    }

    public Routine addRoutine(RoutineAddDTO routineAddDTO, Member member) {
        this.member = member;
        this.routineName = routineAddDTO.getRoutineName();
        this.routineActive = routineAddDTO.isRoutineActive();
        this.totalTime = LocalTime.parse(routineAddDTO.getTotalTime(), DateTimeFormatter.ISO_LOCAL_TIME);
        return this;
    }

    // 루틴 수정
    public void updateRoutine(RoutineUpdateDTO routineUpdateDTO) {
        this.routineName = routineUpdateDTO.getRoutineName();
        this.routineActive = routineUpdateDTO.isRoutineActive();
        this.totalTime = LocalTime.parse(routineUpdateDTO.getTotalTime(), DateTimeFormatter.ISO_LOCAL_TIME);
    }

    // 루틴 수정
    public void updateRoutineSet(RoutineUpdateDTO routineUpdateDTO) {
        this.routineName = routineUpdateDTO.getRoutineName();
        this.routineActive = routineUpdateDTO.isRoutineActive();
    }

}
