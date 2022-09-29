package com.soo.routine.controller.mission;

import com.soo.routine.dto.mission.MissionRecommendAddDTO;
import com.soo.routine.dto.mission.MissionReadDTO;
import com.soo.routine.service.mission.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MissionController {

    private final MissionService missionService;

    /*
    Admin Page
    */

    // 추천 미션 리스트 관리 페이지
    @GetMapping("admin/mission-list")
    public String adminMissionList(Model model) {
        List<MissionReadDTO> lists = missionService.getMissionList("");
        model.addAttribute("lists", lists);
        model.addAttribute("pageName", "Recommend Mission List");
        return "admin/mission_list";
    }

    // 추천 미션 추가 페이지
    @GetMapping("admin/mission-add")
    public String adminMissionAdd(Model model, MissionRecommendAddDTO missionRecommendAddDTO) {
        model.addAttribute("pageName", "Recommend Mission Add");
        return "admin/mission_add";
    }

    // 추천 미션 추가
    @PostMapping("admin/mission-add")
    public String adminMissionAdd(Model model, @Valid MissionRecommendAddDTO missionRecommendAddDTO, BindingResult bindingResult) {

        if (missionRecommendAddDTO.getRunTime().equals("00:00:00")) {
            bindingResult.addError(new FieldError("missionRecommendAddDTO", "runTime", "시간을 입력해주세요."));
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("pageName", "Recommend Mission Add");
            return "admin/mission_add";
        }

        missionService.addRecommendMission(missionRecommendAddDTO);

        return "redirect:/admin/mission-list";
    }

    /*
    User Page
    */

}
