package com.soo.routine.controller;

import com.soo.routine.dto.RoutineDTO;
import com.soo.routine.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("routine")
@RequiredArgsConstructor
@Controller
public class RoutineController {

    private final RoutineService routineService;

    @GetMapping("routine-list")
    public String routineList(Model model, int memberId) {
        List<RoutineDTO> lists = routineService.getRoutineList(memberId);
        model.addAttribute("lists", lists);
        return "routine/routine_list";
    }

    @GetMapping("routine-add")
    public String routineAdd() {
        return "routine/routine_add";
    }

    @PostMapping("routine-add")
    public String routineAdd(RoutineDTO routineDTO) {
        routineService.createRoutine(routineDTO);
        return "routine/routine_add";
    }

    @GetMapping("routine-detail")
    public String routineDetail() {

        return "routine/routine_detail";
    }

    @GetMapping("routine-start")
    public String routineStart() {

        return "routine/routine_start";
    }

    @GetMapping("routine-finish")
    public String routineFinish() {

        return "routine/routine_finish";
    }

    @GetMapping("mission-add")
    public String missionAdd() {

        return "routine/mission_add";
    }

    @GetMapping("mission-list")
    public String missionList() {

        return "routine/mission_list";
    }




}
