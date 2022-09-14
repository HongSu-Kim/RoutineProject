package com.soo.routine.controller;

import com.soo.routine.dto.mission.MissionAddDTO;
import com.soo.routine.dto.mission.MissionReadDTO;
import com.soo.routine.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MissionController {

    private final MissionService missionService;

    // 추천 미션 리스트 페이지
    @GetMapping("admin/mission-list")
    public String adminMissionList(Model model, int routineId) {
        List<MissionReadDTO> lists = missionService.getMissionList(routineId);
        model.addAttribute("lists", lists);
        return "admin/mission_list";
    }

    // 추천 미션 추가 페이지
    @GetMapping("admin/mission-add")
    public String adminMissionAdd(MissionAddDTO missionAddDTO) {
        return "admin/mission_add";
    }

    // 추천 미션 추가
    @PostMapping("admin/mission-add")
    public String adminMissionAdd(Model model, @Valid MissionAddDTO missionAddDTO) {
        missionService.addMission(missionAddDTO);
        return "redirect:/admin/mission-list";
    }





}
