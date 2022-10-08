package com.soo.routine.service.routine;

import com.soo.routine.dto.routine.*;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import com.soo.routine.entity.mission.Mission;
import com.soo.routine.entity.mission.MissionIcon;
import com.soo.routine.entity.routine.Routine;
import com.soo.routine.entity.routine.RoutineSet;
import com.soo.routine.entity.routine.Week;
import com.soo.routine.mapper.routine.RoutineMapper;
import com.soo.routine.repository.member.MemberRepository;
import com.soo.routine.repository.mission.MissionIconRepository;
import com.soo.routine.repository.mission.MissionRepository;
import com.soo.routine.repository.routine.RoutineRepository;
import com.soo.routine.repository.routine.RoutineSetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final RoutineSetRepository routineSetRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MissionIconRepository missionIconRepository;
    private final RoutineMapper routineMapper;
    private final ModelMapper modelMapper;

    // 추천 루틴 추가
    public void addRecommendRoutine(RoutineRecommendAddDTO routineRecommendAddDTO) {

        Member member = memberRepository.findById(routineRecommendAddDTO.getMemberId()).get();

        // save routine
        Routine routine = new Routine(routineRecommendAddDTO, member);
        routineRepository.save(routine);

        // save mission
        if (routineRecommendAddDTO.getMissionIconId() != null) {
            for (int i = 0; i < routineRecommendAddDTO.getMissionIconId().length; i++) {
                MissionIcon missionIcon = missionIconRepository.findById(routineRecommendAddDTO.getMissionIconId()[i]).get();
                Mission mission = new Mission(routine, missionIcon, routineRecommendAddDTO.getMissionName()[i],
                routineRecommendAddDTO.getRuntime()[i], routineRecommendAddDTO.getMissionContent()[i] != null ? routineRecommendAddDTO.getMissionContent()[i] : "");
                missionRepository.save(mission);
            }
        }

    }

    // 루틴 추가
    public void addRoutine(RoutineAddDTO routineAddDTO) {

        Member member = memberRepository.findById(routineAddDTO.getMemberId()).get();

        // save routine
        Routine routine = new Routine(routineAddDTO, member);
        routineRepository.save(routine);

        // save routineSet
        boolean[] weekActive = routineAddDTO.getWeekActive();
        String startTime = routineAddDTO.getStartTime();
        for (int i = 0; i < 7; i++) {
            for (Week w : Week.class.getEnumConstants()) {
                if (w.getValue() == i) {
                    RoutineSet routineSet = new RoutineSet(w , weekActive[i], startTime, routine);
                    routineSetRepository.save(routineSet);
                }
            }
        }

        // save mission
//        if (routineAddDTO.getIconId() != null) {
//            for (int i = 0; i < routineAddDTO.getIconId().length; i++) {
//                MissionIcon missionIcon = missionIconRepository.findById(routineAddDTO.getIconId()[i]).get();
//                Mission mission = new Mission().addRecommend(routine, missionIcon, routineAddDTO.getMissionName()[i],
//                routineAddDTO.getRuntime()[i], routineAddDTO.getMissionContent()[i] != null ? routineAddDTO.getMissionContent()[i] : "");
//                missionRepository.save(mission);
//            }
//        }
    }

    // 루틴 리스트
    @Transactional(readOnly = true)
    public List<RoutineDetailDTO> getRoutineList(Long memberId) {

        List<Routine> routineList = routineRepository.findAllByMemberId(memberId);
        Type type = new TypeToken<List<RoutineDetailDTO>>() {}.getType();

        List<RoutineDetailDTO> lists = modelMapper.map(routineList, type);

        return lists;
    }

    // 루틴 수정
    public void updateRoutine(RoutineUpdateDTO routineUpdateDTO) {

        Routine routine = routineRepository.findById(routineUpdateDTO.getRoutineId()).get();
        routine.updateRoutine(routineUpdateDTO);

        routineRepository.save(routine);
    }

    // 추천 루틴 수정
    public void updateRoutine(RoutineRecommendEditDTO routineRecommendEditDTO) {

        Routine routine = routineRepository.findById(routineRecommendEditDTO.getRoutineId()).get();
        routine.updateRoutine(routineRecommendEditDTO);

        Long[] missionId = routineRecommendEditDTO.getMissionId();
        Long[] missionIconId = routineRecommendEditDTO.getMissionIconId();
        String[] missionName = routineRecommendEditDTO.getMissionName();
        String[] runTime = routineRecommendEditDTO.getRunTime();
        String[] missionContent = routineRecommendEditDTO.getMissionContent();

        // update, delete
        for (Mission m : routine.getMissionList()) {
            boolean result = false;
            for (int i = 0; i < missionId.length; i++) {
                if (m.getId().equals(missionId[i])) {
                    Mission mission = missionRepository.findById(m.getId()).get();
                    MissionIcon missionIcon = missionIconRepository.findById(missionIconId[i]).get();
                    mission.edit(missionIcon, missionName[i], runTime[i], missionContent[i]);
                    missionRepository.save(mission);
                    result = true;
                }
            }
            if (!result) {
                missionRepository.deleteById(m.getId());
            }
        }

        // insert
        for (int i = 0; i < missionId.length; i++) {
            if (missionId[i] == null) {
                MissionIcon missionIcon = missionIconRepository.findById(missionIconId[i]).get();
                Mission mission = new Mission(routine, missionIcon, missionName[i], runTime[i], missionContent[i]);
                missionRepository.save(mission);
            }
        }

        routineRepository.save(routine);
    }
    
    // 루틴세팅 수정
    public void updateRoutineSet(RoutineUpdateDTO routineUpdateDTO) {

        Routine routine = routineRepository.findById(routineUpdateDTO.getRoutineId()).get();
        routine.updateRoutineSet(routineUpdateDTO);

        routineRepository.save(routine);

        int i = 0;
        for (boolean weekActive : routineUpdateDTO.getWeekActive()) {
            for (RoutineSet rs : routine.getRoutineSetList()) {
                if (rs.getWeek().getValue() == i) {
                    rs.updateRoutineSet(weekActive, routineUpdateDTO.getStartTime());

                    routineSetRepository.save(rs);
                    i++;
                    break;
                }
            }
        }
    }

    // 루틴 추천 리스트
    @Transactional(readOnly = true)
    public List<RoutineDetailDTO> getRecommendRoutineList() {

        List<Routine> routineList = routineRepository.findAllByMemberRole(Role.ADMIN);
        Type type = new TypeToken<List<RoutineDetailDTO>>() {}.getType();

        List<RoutineDetailDTO> lists = modelMapper.map(routineList, type);

        return lists;
    }

    // 루틴 디테일
    @Transactional(readOnly = true)
    public RoutineDetailDTO getRoutine(Long routineId) {

        Routine routine = routineRepository.findById(routineId).orElse(null);

        if (routine == null) {
            return null;
        }

        RoutineDetailDTO routineDetailDTO = modelMapper.map(routine, RoutineDetailDTO.class);
        String today = LocalDate.now().getDayOfWeek().name();

        for (RoutineSet rs : routine.getRoutineSetList()) {
            if (today.equals(rs.getWeek().name())){

                LocalTime totalTime = routine.getTotalTime();
                LocalTime finalTime = rs.getStartTime();

                finalTime.plusHours(totalTime.getHour());
                finalTime.plusMinutes(totalTime.getMinute());
                finalTime.plusSeconds(totalTime.getSecond());

                routineDetailDTO.setFinalTime(finalTime);
                routineDetailDTO.setRoutineSet(rs);
            }
        }

        return routineDetailDTO;
    }

    // 루틴 수정 select
    public RoutineUpdateDTO getRoutineUpdateDTO(Long routineId) {

        Routine routine = routineRepository.findById(routineId).orElse(null);

        if (routine == null) {
            return null;
        }

        RoutineUpdateDTO routineUpdateDTO = modelMapper.map(routine, RoutineUpdateDTO.class);

        boolean[] weekActive = new boolean[7];
        for (Week w : Week.class.getEnumConstants()) {
            for (RoutineSet rs : routine.getRoutineSetList()) {
                if (w.name().equals(rs.getWeek().name())) {
                    weekActive[w.getValue()] = rs.isWeekActive();

                    if (w.getValue() == 0) {
                        routineUpdateDTO.setStartTime(rs.getStartTime().toString());
                    }
                }
            }
        }

        routineUpdateDTO.setWeekActive(weekActive);

        return routineUpdateDTO;
    }

    // 추천 루틴 수정 select
    public RoutineRecommendEditDTO getRecommendRoutine(Long routineId) {

        Routine routine = routineRepository.findById(routineId).orElse(null);

        if (routine == null) {
            return null;
        }

        RoutineRecommendEditDTO routineRecommendEditDTO = modelMapper.map(routine, RoutineRecommendEditDTO.class);

        return routineRecommendEditDTO;
    }
}
