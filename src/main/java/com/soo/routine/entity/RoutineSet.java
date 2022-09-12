package com.soo.routine.entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Getter
public class RoutineSet implements Serializable {

    @Id
    private String week;//요일 PK

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;//루틴번호 PK FK

    private LocalTime startTime;//시작시간

}
