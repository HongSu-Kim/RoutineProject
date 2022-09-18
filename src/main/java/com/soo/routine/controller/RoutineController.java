package com.soo.routine.controller;

import com.soo.routine.dto.routine.RoutineAddDTO;
import com.soo.routine.dto.routine.RoutineReadDTO;
import com.soo.routine.dto.routine.RoutineUpdateDTO;
import com.soo.routine.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class RoutineController {

    private final RoutineService routineService;

    // admin - 추천 루틴 리스트 관리 페이지
    @GetMapping("admin/routine-list")
    public String adminRoutineList(Model model) {
        int memberId = 1;
        List<RoutineReadDTO> lists = routineService.getRoutineList(memberId);
        model.addAttribute("lists", lists);
        model.addAttribute("pageName", "Routine List");
        return "admin/routine_list";
    }

    // admin - 추천 루틴 추가 페이지
    @GetMapping("admin/routine-add")
    public String adminRoutineAdd(Model model, RoutineAddDTO routineAddDTO) {
        model.addAttribute("pageName", "Routine Add");
        return "admin/routine_add";
    }

    // admin - 추천 루틴 추가
    @PostMapping("admin/routine-add")
    public String adminRoutineAdd(Model model, @Valid RoutineAddDTO routineAddDTO, BindingResult bindingResult) {
        int memberId = 1;
        routineService.addRoutine(routineAddDTO);
        List<RoutineReadDTO> lists = routineService.getRoutineList(memberId);
        model.addAttribute("lists", lists);
        return "redirect:/admin/routine-list";
    }

    // admin - 추천 루틴 수정 페이지
    @GetMapping("admin/routine-update")
    public String adminRoutineUpdate(Model model, RoutineUpdateDTO routineUpdateDTO) {
        model.addAttribute("pageName", "Routine Update");
        return "admin/routine_add";
    }

    // admin - 추천 루틴 수정
    @PostMapping("admin/routine-update")
    public String adminRoutineUpdate(Model model, @Valid RoutineUpdateDTO routineUpdateDTO, BindingResult bindingResult) {
        int memberId = 1;
        routineService.updateRoutine(routineUpdateDTO);
        List<RoutineReadDTO> lists = routineService.getRoutineList(memberId);
        model.addAttribute("lists", lists);
        return "redirect:/admin/routine-list";
    }

    // 루틴 리스트 페이지
    @GetMapping("routine/routine-list")
    public String routineList(Model model) {
        int memberId = 1;
        List<RoutineReadDTO> lists = routineService.getRoutineList(memberId);
        model.addAttribute("lists", lists);
        return "user/routine/routine_list";
    }

    // 루틴 추가 페이지
    @GetMapping("routine/routine-add")
    public String routineAdd() {
        return "routine/routine_add";
    }

    // 루틴 추가
    @PostMapping("routine/routine-add")
    public String routineAdd(RoutineAddDTO routineAddDTO) {
        routineService.addRoutine(routineAddDTO);
        return "routine/routine_add";
    }

    // 루틴 상세 페이지
    @GetMapping("routine/routine-detail")
    public String routineDetail() {

        return "routine/routine_detail";
    }

    // 루틴 실행 페이지
    @GetMapping("routine/routine-start")
    public String routineStart() {

        return "routine/routine_start";
    }

    // 루틴 종료 페이지
    @GetMapping("routine/routine-finish")
    public String routineFinish() {

        return "routine/routine_finish";
    }

    // 미션 리스트 페이지
    @GetMapping("routine/mission-list")
    public String missionList() {

        return "routine/mission_list";
    }

    // 미션 추가 페이지
    @GetMapping("routine/mission-add")
    public String missionAdd() {

        return "routine/mission_add";
    }




}
