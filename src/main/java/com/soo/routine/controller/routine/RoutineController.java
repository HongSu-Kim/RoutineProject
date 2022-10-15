package com.soo.routine.controller.routine;

import com.soo.routine.dto.mission.IconCategoryDTO;
import com.soo.routine.dto.mission.MissionIconDTO;
import com.soo.routine.dto.mission.MissionStartDTO;
import com.soo.routine.dto.routine.*;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import com.soo.routine.entity.routine.Week;
import com.soo.routine.service.mission.IconCategoryService;
import com.soo.routine.service.mission.MissionIconService;
import com.soo.routine.service.mission.MissionService;
import com.soo.routine.service.routine.RoutineService;
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
public class RoutineController {

    private final RoutineService routineService;
	private final MissionService missionService;
    private final IconCategoryService iconCategoryService;
    private final MissionIconService missionIconService;
    private final HttpSession httpSession;

    /*
    Admin Page
    */

    // admin - 추천 루틴 리스트 관리 페이지
    @GetMapping("admin/routine-list")
    public String adminRoutineList(Model model, @PageableDefault Pageable pageable) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            return "redirect:/login";
        }

        Page<RoutineDTO> lists = routineService.getRecommendRoutineList(pageable);

        model.addAttribute("lists", lists);
        model.addAttribute("pageName", "Routine List");
        return "admin/routine/list";
    }

    // admin - 추천 루틴 추가 페이지
    @GetMapping("admin/routine-add")
    public String adminRoutineAdd(Model model, RoutineRecommendAddDTO routineRecommendAddDTO) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            return "redirect:/login";
        }

        routineRecommendAddDTO.setMemberId(loginMember.getId());
        routineRecommendAddDTO.setMemberNickname(loginMember.getNickname());

        List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        List<MissionIconDTO> iconList = missionIconService.getIconList();
        model.addAttribute("iconList", iconList);

        model.addAttribute("pageName", "Routine Add");
        return "admin/routine/add";
    }

    // admin - 추천 루틴 추가
    @PostMapping("admin/routine-add")
    public String adminRoutineAdd(Model model, @Valid RoutineRecommendAddDTO routineRecommendAddDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
            List<MissionIconDTO> iconList = missionIconService.getIconList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("iconList", iconList);
            model.addAttribute("pageName", "Routine Add");
            return "admin/routine/add";
        }

        routineRecommendAddDTO.setMemberId(routineRecommendAddDTO.getMemberId());
        routineService.addRecommendRoutine(routineRecommendAddDTO);

        return "redirect:/admin/routine-list";
    }

    // admin - 추천 루틴 수정 페이지
    @GetMapping("admin/routine-edit")
    public String adminRoutineUpdate(Model model, RoutineRecommendEditDTO routineRecommendEditDTO, Long routineId) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            return "redirect:/login";
        }

        routineRecommendEditDTO = routineService.getRecommendRoutine(routineId);
        List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
        List<MissionIconDTO> iconList = missionIconService.getIconList();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("iconList", iconList);
        model.addAttribute("routineRecommendEditDTO", routineRecommendEditDTO);
        model.addAttribute("pageName", "Routine Edit");
        return "admin/routine/edit";
    }

    // admin - 추천 루틴 수정
    @PostMapping("admin/routine-edit")
    public String adminRoutineUpdate(Model model, @Valid RoutineRecommendEditDTO routineRecommendEditDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<IconCategoryDTO> categoryList = iconCategoryService.getCategoryList();
            List<MissionIconDTO> iconList = missionIconService.getIconList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("iconList", iconList);
            model.addAttribute("pageName", "Routine Edit");
            return "admin/routine/edit";
        }

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        routineService.updateRoutine(routineRecommendEditDTO);
        List<RoutineDTO> lists = routineService.getRoutineList(loginMember.getId());

        model.addAttribute("lists", lists);
        return "redirect:/admin/routine-edit?routineId=" + routineRecommendEditDTO.getRoutineId();
    }

    /*
    User Page
    */

    // 루틴 리스트 페이지(메인)
    @GetMapping("routine")
    public String routineList(Model model) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        List<RoutineDTO> lists = routineService.getRoutineList(loginMember.getId());

        model.addAttribute("lists", lists);

        return "routine/routine/list";
    }

    // 루틴 추가 페이지
    @GetMapping("routine-add")
    public String routineAdd(Model model, RoutineAddDTO routineAddDTO) {

        if (httpSession.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

		boolean[] weekActive = new boolean[7];
		String[] weekButton = new String[7];
		int i = 0;

		for (Week w : Week.class.getEnumConstants()) {
			weekActive[i] = w.isBool();
			weekButton[i] = w.getButton();
			i++;
		}

		routineAddDTO.setWeekActive(weekActive);
		routineAddDTO.setWeekButton(weekButton);

        return "routine/routine/add";
    }

    // 루틴 추가
    @PostMapping("routine-add")
    public String routineAdd(Model model, @Valid RoutineAddDTO routineAddDTO, BindingResult bindingResult) {

		boolean weekActive = false;
		for (boolean wa : routineAddDTO.getWeekActive()) {
			if (wa)
				weekActive = wa;
		}

		if (!weekActive) {
			bindingResult.addError(new FieldError("routineAddDTO", "weekActive", "요일을 하나 이상 선택해주세요"));
		}

        if (bindingResult.hasErrors()) {
			model.addAttribute("routineAddDTO", routineAddDTO);
            return "routine/routine/add";
        }

        Member loginMember = (Member) httpSession.getAttribute("loginMember");
        routineAddDTO.setMemberId(loginMember.getId());

        routineService.addRoutine(routineAddDTO);

        return "redirect:/routine";
    }

    // 루틴 상세 페이지
    @GetMapping("routine-detail")
    public String routineDetail(Model model, Long routineId) {

        if (httpSession.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        RoutineDTO routineDTO = routineService.getRoutine(routineId);

        if (routineDTO == null) {
            return "redirect:/routine";
        }

        model.addAttribute("routineDTO" , routineDTO);

        return "routine/routine/detail";
    }

    // 루틴 수정 페이지
    @GetMapping("routine-edit")
    public String routineEdit(Model model, RoutineUpdateDTO routineUpdateDTO, Long routineId) {

        if (httpSession.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        routineUpdateDTO = routineService.getRoutineUpdateDTO(routineId);

        model.addAttribute("weekEnum", Week.class.getEnumConstants());
        model.addAttribute("routineUpdateDTO", routineUpdateDTO);
        return "routine/routine/edit";
    }

    // 루틴 수정
    @PostMapping("routine-edit")
    public String routineEdit(Model model, @Valid RoutineUpdateDTO routineUpdateDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("weekEnum", Week.class.getEnumConstants());
            return "routine/routine/edit";
        }

        routineService.updateRoutineSet(routineUpdateDTO);
        routineService.updateRoutine(routineUpdateDTO);

        return "redirect:/routine";
    }

    // 루틴 실행 페이지
    @GetMapping("routine-start")
    public String routineStart(Model model, Long routineId) {

        if (httpSession.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

//		RoutineDTO routineDTO = routineService.getRoutine(routineId);
//		List<MissionReadDTO> list = missionService.getMissionList(routineId);
		MissionStartDTO missionStartDTO = missionService.getNextMissionStartDTO(routineId, null);

		if (missionStartDTO == null) {
			return "redirect:/routine";
		}

//		model.addAttribute("routineReadDTO", routineDTO);
//		model.addAttribute("list", list);
		model.addAttribute("missionStartDTO", missionStartDTO);
		return "routine/routine/start";
    }

    // 루틴 종료 페이지
    @GetMapping("routine-finish")
    public String routineFinish(Model model, Long routineId) {

		RoutineDTO routineDTO = routineService.getRoutine(routineId);

		model.addAttribute("routineDTO", routineDTO);
        return "routine/routine/finish";
    }

    // 루틴 활성화 수정
    @PostMapping("routine-activate")
    public String routineActive(Long activateId) {

        routineService.routineActive(activateId);
        return "redirect:/routine";
    }

    // 루틴 삭제
    @PostMapping("routine-delete")
    public String routineDelete(Long deleteId) {

        routineService.routineDelete(deleteId);
        return "redirect:/routine";
    }
}