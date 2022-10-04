package com.soo.routine.entity.routine;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@IdClass(RoutineSetId.class)
public class RoutineSet implements Serializable {

    @Id
    @Enumerated(EnumType.STRING)
    private Week week;//요일 PK

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;//루틴번호 PK FK

    private boolean weekActive;//요일활성화
    private LocalTime startTime;//시작시간

    // 루틴 세팅 추가
    public RoutineSet(Week week, boolean weekActive, String startTime, Routine routine) {
        this.week = week;
        this.routine = routine;
        this.weekActive = weekActive;
        this.startTime = LocalTime.parse(startTime);
    }

    // 루틴 세팅 수정
    public void updateRoutineSet(boolean weekActive, String startTime) {
        this.weekActive = weekActive;
        this.startTime = LocalTime.parse(startTime);
    }
}
