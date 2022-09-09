package com.soo.routine.controller;

import com.soo.routine.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MissionController {

    private final MissionService missionService;

}
