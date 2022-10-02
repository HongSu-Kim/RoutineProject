package com.soo.routine.controller.routine;

import com.soo.routine.dto.routine.RoutineAddDTO;
import com.soo.routine.dto.routine.RoutineReadDTO;
import com.soo.routine.dto.routine.RoutineUpdateDTO;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import com.soo.routine.entity.routine.Week;
import com.soo.routine.service.routine.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class RoutineController {

    private final RoutineService routineService;
    private final HttpSession httpSession;

    /*
    Admin Page
    */

    // admin - 추천 루틴 리스트 관리 페이지
    @GetMapping("admin/routine-list")
    public String adminRoutineList(Model model) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            return "redirect:/";
        }

        List<RoutineReadDTO> lists = routineService.getRecommendRoutineList();

        model.addAttribute("lists", lists);
        model.addAttribute("pageName", "Routine List");
        return "admin/routine_list";
    }

    // admin - 추천 루틴 추가 페이지
    @GetMapping("admin/routine-add")
    public String adminRoutineAdd(Model model, RoutineAddDTO routineAddDTO) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            return "redirect:/";
        }

        model.addAttribute("pageName", "Routine Add");
        return "admin/routine_add";
    }

    // admin - 추천 루틴 추가
    @PostMapping("admin/routine-add")
    public String adminRoutineAdd(Model model, @Valid RoutineAddDTO routineAddDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("pageName", "Routine Add");
            return "admin/routine_add";
        }

        Member loginMember = (Member) httpSession.getAttribute("loginMember");
        routineAddDTO.setMemberId(loginMember.getId());
        routineService.addRoutine(routineAddDTO);

        return "redirect:/admin/routine-list";
    }

    // admin - 추천 루틴 수정 페이지
    @GetMapping("admin/routine-update")
    public String adminRoutineUpdate(Model model, RoutineUpdateDTO routineUpdateDTO) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            return "redirect:/";
        }

        model.addAttribute("mode", "update");
        model.addAttribute("pageName", "Routine Update");
        return "admin/routine_add";
    }

    // admin - 추천 루틴 수정
    @PostMapping("admin/routine-update")
    public String adminRoutineUpdate(Model model, @Valid RoutineUpdateDTO routineUpdateDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "update");
            model.addAttribute("pageName", "Routine Update");
            return "admin/routine_add";
        }

        Long memberId = 1L;
        routineService.updateRoutine(routineUpdateDTO);
        List<RoutineReadDTO> lists = routineService.getRoutineList(memberId);

        model.addAttribute("lists", lists);
        return "redirect:/admin/routine-list";
    }

    /*
    User Page
    */

    // 루틴 리스트 페이지(메인)
    @GetMapping("routine")
    public String routineList(Model model) {

        Member loginMember = (Member) httpSession.getAttribute("loginMember");

        if (loginMember == null) {
            return "redirect:/login";
        }

        List<RoutineReadDTO> lists = routineService.getRoutineList(loginMember.getId());

        model.addAttribute("lists", lists);
        return "routine/routine_list";
    }

    // 루틴 추가 페이지
    @GetMapping("routine-add")
    public String routineAdd(Model model, RoutineAddDTO routineAddDTO) {

        if (httpSession.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        model.addAttribute("weekEnum", Week.class.getEnumConstants());

        return "routine/routine_add";
    }

    // 루틴 추가
    @PostMapping("routine-add")
    public String routineAdd(Model model, @Valid RoutineAddDTO routineAddDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("weekEnum", Week.class.getEnumConstants());
            return "routine/routine_add";
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

        RoutineReadDTO routineReadDTO = routineService.getRoutine(routineId);

        if (routineReadDTO == null) {
            return "redirect:/routine";
        }

        model.addAttribute("routineDTO" , routineReadDTO);
        return "routine/routine_detail";
    }

    // 루틴 실행 페이지
    @GetMapping("routine-start")
    public String routineStart() {

        if (httpSession.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        return "routine/routine_start";
    }

    // 루틴 종료 페이지
    @GetMapping("routine-finish")
    public String routineFinish() {

        return "routine/routine_finish";
    }

}
