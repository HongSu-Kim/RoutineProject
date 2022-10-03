package com.soo.routine.entity.routine;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Getter
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

    public RoutineSet addRoutineSet(int i, boolean weekActive, String startTime, Routine routine) {
        for (Week w : Week.class.getEnumConstants()) {
            if (w.getValue() == i) this.week = w;
        }
        this.routine = routine;
        this.weekActive = weekActive;
        this.startTime = LocalTime.parse(startTime);
        return this;
    }

    public void updateRoutineSet(boolean weekActive, String startTime) {
        this.weekActive = weekActive;
        this.startTime = LocalTime.parse(startTime);
    }


}
