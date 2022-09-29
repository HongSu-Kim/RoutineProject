package com.soo.routine.controller.routine;

import com.soo.routine.dto.routine.RoutineAddDTO;
import com.soo.routine.dto.routine.RoutineReadDTO;
import com.soo.routine.dto.routine.RoutineUpdateDTO;
import com.soo.routine.dto.routine.Week;
import com.soo.routine.entity.member.Member;
import com.soo.routine.service.routine.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Iterator;
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

        List<RoutineReadDTO> lists = routineService.getRecommendRoutineList();

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
    public String adminRoutineAdd(Model model, @Valid RoutineAddDTO routineAddDTO, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getErrorCount());
            List<ObjectError> list = bindingResult.getAllErrors();
            Iterator<ObjectError> it = list.iterator();
            System.out.println(request.getAttribute("runtime"));
            System.out.println(request.getAttribute("missionName"));
            while (it.hasNext()){
                System.out.println(it.next());
            }
            model.addAttribute("pageName", "Routine Add");
            return "admin/routine_add";
        }

        routineService.addRoutine(routineAddDTO);

        return "redirect:/admin/routine-list";
    }

    // admin - 추천 루틴 수정 페이지
    @GetMapping("admin/routine-update")
    public String adminRoutineUpdate(Model model, RoutineUpdateDTO routineUpdateDTO) {
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

        List<RoutineReadDTO> lists = routineService.getRoutineList(loginMember.getId());

        model.addAttribute("lists", lists);
        return "routine/routine_list";
    }

    // 루틴 추가 페이지
    @GetMapping("routine/routine-add")
    public String routineAdd(Model model, RoutineAddDTO routineAddDTO) {

        Week sun = Week.SUN;

        return "routine/routine_add";
    }

    // 루틴 추가
    @PostMapping("routine/routine-add")
    public String routineAdd(@Valid RoutineAddDTO routineAddDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/routine/routine-add";
        }

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
