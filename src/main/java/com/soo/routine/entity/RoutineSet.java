package com.soo.routine.entity;

import com.soo.routine.dto.routine.Week;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Getter
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

}
