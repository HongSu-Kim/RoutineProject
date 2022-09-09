package com.soo.routine.entity;

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
    @ManyToOne
    @JoinColumn(name = "routine_num")
    private Routine routine;//루틴번호 PK FK

    private LocalTime startTime;//시작시간

}
