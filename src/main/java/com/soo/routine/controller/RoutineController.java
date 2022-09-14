package com.soo.routine.controller;

import com.soo.routine.dto.routine.RoutineAddDTO;
import com.soo.routine.dto.routine.RoutineReadDTO;
import com.soo.routine.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class RoutineController {

    private final RoutineService routineService;

    @GetMapping("admin/routine-list")
    public String adminRoutineList(Model model) {
        int memberId = 1;
        List<RoutineReadDTO> lists = routineService.getRoutineList(memberId);
        model.addAttribute("lists", lists);
        return "admin/routine_list";
    }

    @GetMapping("admin/routine-add")
    public String adminRoutineAdd(RoutineAddDTO routineAddDTO) {
        return "admin/routine_add";
    }

    @PostMapping("admin/routine-add")
    public String adminRoutineAdd(Model model, @Valid RoutineAddDTO routineAddDTO) {
        int memberId = 1;
        routineService.addRoutine(routineAddDTO);
        List<RoutineReadDTO> lists = routineService.getRoutineList(memberId);
        model.addAttribute("lists", lists);
        return "redirect:/admin/routine-list";
    }

    @GetMapping("routine/routine-list")
    public String routineList(Model model) {
        int memberId = 1;
        List<RoutineReadDTO> lists = routineService.getRoutineList(memberId);
        model.addAttribute("lists", lists);
        return "user/routine/routine_list";
    }

    @GetMapping("routine/routine-add")
    public String routineAdd() {
        return "routine/routine_add";
    }

    @PostMapping("routine/routine-add")
    public String routineAdd(RoutineAddDTO routineAddDTO) {
        routineService.addRoutine(routineAddDTO);
        return "routine/routine_add";
    }

    @GetMapping("routine/routine-detail")
    public String routineDetail() {

        return "routine/routine_detail";
    }

    @GetMapping("routine/routine-start")
    public String routineStart() {

        return "routine/routine_start";
    }

    @GetMapping("routine/routine-finish")
    public String routineFinish() {

        return "routine/routine_finish";
    }

    @GetMapping("routine/mission-add")
    public String missionAdd() {

        return "routine/mission_add";
    }

    @GetMapping("routine/mission-list")
    public String missionList() {

        return "routine/mission_list";
    }




}
