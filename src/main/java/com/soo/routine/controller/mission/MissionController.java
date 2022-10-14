package com.soo.routine.controller.mission;

import com.soo.routine.dto.mission.*;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import com.soo.routine.service.mission.IconCategoryService;
import com.soo.routine.service.mission.MissionIconService;
import com.soo.routine.service.mission.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MissionController {

    private final MissionService missionService;
    private final MissionIconService missionIconService;
    private final IconCategoryService iconCategoryService;
    private final HttpSession httpSession;

    /*
    Admin Page
    */

    // 추천 미션 리스트 관리 페이지
    @GetMapping("admin/mission-list")
    public String adminMissionList(Model model, @PageableDefault Pageable pageable) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            return "redirect:/startRoutine";
        }

        Page<MissionReadDTO> lists = missionService.getMissionPage(pageable);

        model.addAttribute("lists", lists);
        model.addAttribute("pageName", "Recommend Mission List");
        return "admin/mission/list";
    }

    // 추천 미션 추가 페이지
    @GetMapping("admin/mission-add")
    public String adminMissionAdd(Model model, MissionRecommendAddDTO missionRecommendAddDTO) {

        List<MissionIconDTO> iconList = missionIconService.getIconList();
        List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();

        model.addAttribute("iconList", iconList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("pageName", "Recommend Mission Add");
        return "admin/mission/add";
    }

    // 추천 미션 추가
    @PostMapping("admin/mission-add")
    public String adminMissionAdd(Model model, @Valid MissionRecommendAddDTO missionRecommendAddDTO, BindingResult bindingResult) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            return "redirect:/startRoutine";
        }

        if (missionRecommendAddDTO.getRunTime().equals("00:00")) {
            bindingResult.addError(new FieldError("missionRecommendAddDTO", "runTime", "시간을 입력해주세요."));
        }

        if (bindingResult.hasErrors()) {
            List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
            List<MissionIconDTO> iconList = missionIconService.getIconList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("iconList", iconList);
            model.addAttribute("pageName", "Recommend Mission Add");
            return "admin/mission/add";
        }

        missionService.addRecommendMission(missionRecommendAddDTO);

        return "redirect:/admin/mission-list";
    }

    // 추천 미션 수정 페이지
    @GetMapping("admin/mission-edit")
    public String adminMissionEdit(Model model, MissionRecommendEditDTO missionRecommendEditDTO, Long missionId) {

        MissionReadDTO missionReadDTO = missionService.getMission(missionId);
        List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
        List<MissionIconDTO> iconList = missionIconService.getIconList();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("iconList", iconList);
        model.addAttribute("missionRecommendEditDTO", missionReadDTO);
        model.addAttribute("pageName", "Recommend Mission Edit");
        return "admin/mission/edit";
    }

    @PostMapping("admin/mission-edit")
    public String adminMissionEdit(Model model, @Valid MissionRecommendEditDTO missionRecommendEditDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
            List<MissionIconDTO> iconList = missionIconService.getIconList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("iconList", iconList);
            model.addAttribute("pageName", "Recommend Mission Edit");
            return "admin/mission/edit";
        }

        missionService.editRecommendMission(missionRecommendEditDTO);

        return "redirect:/admin/mission-list";
    }

    @GetMapping("admin/mission-delete")
    public String adminMissionDelete(Long missionId) {

        missionService.deleteMission(missionId);

        return "redirect:/admin/mission-list";
    }

    /*
    User Page
    */

    // 미션 추가
    @GetMapping("mission-add")
    public String missionAdd(Model model, MissionAddDTO missionAddDTO, Long routineId) {

        if (httpSession.getAttribute("loginMember") == null){
            return "redirect:/login";
        }

        List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
        List<MissionIconDTO> iconList = missionIconService.getIconList();

        model.addAttribute("routineId", routineId);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("iconList", iconList);
        return "routine/mission/add";
    }

    @PostMapping("mission-add")
    public String missionAdd(Model model, @Valid MissionAddDTO missionAddDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
            List<MissionIconDTO> iconList = missionIconService.getIconList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("iconList", iconList);
            return "routine/mission/add";
        }

        missionService.addMission(missionAddDTO);

        return "redirect:/routine-detail?routineId=" + missionAddDTO.getRoutineId();
    }

    // 미션 수정
    @GetMapping("mission-edit")
    public String missionEdit(MissionAddDTO missionAddDTO, Long routineId, Long missionId, Model model) {

        List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
        List<MissionIconDTO> iconList = missionIconService.getIconList();
        MissionReadDTO missionReadDTO = missionService.getMission(missionId);

        model.addAttribute("routineId", routineId);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("iconList", iconList);
        model.addAttribute("missionAddDTO", missionAddDTO);

        return "routine/mission/edit";
    }
    @PostMapping("mission-edit")
    public String missionEdit(@Valid MissionAddDTO missionAddDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
            List<MissionIconDTO> iconList = missionIconService.getIconList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("iconList", iconList);

            return "routine/mission/edit";
        }

        missionService.addMission(missionAddDTO);

        return "redirect:/routine-detail?routineId=" + missionAddDTO.getRoutineId();
    }

}
