package com.soo.routine.service.routine;

import com.soo.routine.dto.mission.MissionReadDTO;
import com.soo.routine.dto.routine.*;
import com.soo.routine.entity.member.Member;
import com.soo.routine.entity.member.Role;
import com.soo.routine.entity.mission.Mission;
import com.soo.routine.entity.mission.MissionIcon;
import com.soo.routine.entity.routine.Routine;
import com.soo.routine.entity.routine.RoutineSet;
import com.soo.routine.entity.routine.Week;
import com.soo.routine.repository.member.MemberRepository;
import com.soo.routine.repository.mission.MissionIconRepository;
import com.soo.routine.repository.mission.MissionRepository;
import com.soo.routine.repository.routine.RoutineRepository;
import com.soo.routine.repository.routine.RoutineSetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
				Mission mission = new Mission(routine, missionIcon, routineRecommendAddDTO.getMissionName()[i], routineRecommendAddDTO.getRunTime()[i],
						routineRecommendAddDTO.getMissionContent() != null ? routineRecommendAddDTO.getMissionContent()[i] : "");
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
    public List<RoutineDTO> getRoutineList(Long memberId) {

        List<Routine> routineList = routineRepository.findAllByMemberId(memberId);
        List<RoutineDTO> lists = new ArrayList<>();

        for(Routine routine : routineList) { // routineList에서 routine를 하나씩 꺼낸다. 루틴 for문

            RoutineDTO routineDTO = new RoutineDTO(routine);
            String today = LocalDate.now().getDayOfWeek().name();

            StringBuffer weekList = new StringBuffer();

            for (Week week : Week.class.getEnumConstants()) { // Week Class를 list로 불러와서 --1

                for (RoutineSet routineSet : routine.getRoutineSetList()) { // 요일 for문을 돌리는데

                    if (week == routineSet.getWeek()) {// 요일을 Week의 순서대로 출력한다 --2

                        if (today.equals(routineSet.getWeek().name())) {

                            LocalTime totalTime = routine.getTotalTime();

                            // totalTime의 Hour, Minute, Second를 각각 finalTime에 더해서
                            LocalTime finalTime = routineSet.getStartTime().plusHours(totalTime.getHour()).plusMinutes(totalTime.getMinute());

                            // routineDTO에 저장한다
                            routineDTO.setFinalTime(finalTime);
                            routineDTO.setWeekActive(routineSet.isWeekActive());
                            routineDTO.setStartTime(routineSet.getStartTime());
                        }

                        if (routineSet.isWeekActive()) {
                            weekList.append(routineSet.getWeek().getButton() + ", ");
                        }
                    }
                }
            }

            if (weekList.toString().equals("")) { // 모든 요일이 비활성화일 때
                continue; // continue 아래는 실행X → 다음 routine for문을 실행한다
            }

            String weekResult = "(" + weekList.toString().substring(0, weekList.length() - 2) + ")";

            routineDTO.setWeekList(weekResult);
            lists.add(routineDTO);
        }

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

		if (missionId != null) {
			// update, delete
			for (Mission m : routine.getMissionList()) {
				boolean result = false;
				for (int i = 0; i < missionId.length; i++) {
					if (m.getId().equals(missionId[i])) {
						Mission mission = missionRepository.findById(m.getId()).get();
						MissionIcon missionIcon = missionIconRepository.findById(missionIconId[i]).get();
						mission.edit(missionIcon, missionName[i], runTime[i],
								missionContent.length > 0 && missionContent[i] != null ? missionContent[i] : "");
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
					Mission mission = new Mission(routine, missionIcon, missionName[i], runTime[i],
							missionContent != null && missionContent[i] != null ? missionContent[i] : "");
					missionRepository.save(mission);
				}
			}
		} else {
			for (Mission m : routine.getMissionList()) {
				missionRepository.delete(m);
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
    public Page<RoutineDTO> getRecommendRoutineList(Pageable pageable) {

		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by(sorts));

		return routineRepository.findAllByMemberRole(Role.ADMIN, pageable)
				.map(RoutineDTO::new);
    }

    // 루틴 디테일
    @Transactional(readOnly = true)
    public RoutineDTO getRoutine(Long routineId) {

        Routine routine = routineRepository.findById(routineId).orElse(null);

        if (routine == null) {
            return null;
        }

        RoutineDTO routineDTO = new RoutineDTO(routine);
        String today = LocalDate.now().getDayOfWeek().name();

        for (RoutineSet rs : routine.getRoutineSetList()) {
            if (today.equals(rs.getWeek().name())){

                LocalTime totalTime = routine.getTotalTime();
                LocalTime finalTime = rs.getStartTime().plusHours(totalTime.getHour()).plusMinutes(totalTime.getMinute());

                routineDTO.setFinalTime(finalTime);
				routineDTO.setWeek(rs.getWeek());
				routineDTO.setWeekActive(rs.isWeekActive());
				routineDTO.setStartTime(rs.getStartTime());
            }
        }

        return routineDTO;
    }

    // 루틴 수정 select
    public RoutineUpdateDTO getRoutineUpdateDTO(Long routineId) {

        Routine routine = routineRepository.findById(routineId).orElse(null);

        if (routine == null) {
            return null;
        }

        RoutineUpdateDTO routineUpdateDTO = new RoutineUpdateDTO(routine);

        boolean[] weekActive = new boolean[7];
        for (Week w : Week.class.getEnumConstants()) {
            for (RoutineSet rs : routine.getRoutineSetList()) {
                if (w.name().equals(rs.getWeek().name())) {
                    weekActive[w.getValue()] = rs.isWeekActive();
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

    // 루틴 활성화 수정
    public void routineActive(Long routineId) {

        Routine routine = routineRepository.findById(routineId).get();
        routine.editActive();

        routineRepository.save(routine);
    }

    // 루틴 삭제
    @Transactional
    public void routineDelete(Long routineId) {
        routineRepository.deleteById(routineId);
    }

	public RoutineFinishDTO getRoutineFinishDTO(Long routineId, String[] remainingTime) {

		RoutineFinishDTO routineFinishDTO = routineRepository.findById(routineId).map(RoutineFinishDTO::new).orElse(null);

		LocalTime totalTime = routineFinishDTO.getTotalTime();
		routineFinishDTO.setFinalTime(routineFinishDTO.getStartTime().plusHours(totalTime.getHour()).plusMinutes(totalTime.getMinute()));

		String[] elapsedTime = new String[routineFinishDTO.getMissionList().size()];


		int i = 0;
		for (MissionReadDTO missionReadDTO : routineFinishDTO.getMissionList()) {

			int time = Integer.parseInt(remainingTime[i]);
			remainingTime[i] = toString(time);

			time = missionReadDTO.getRunTime().toSecondOfDay() - time;
			elapsedTime[i] = toString(time);

			i++;
		}

		routineFinishDTO.setElapsedTime(elapsedTime);
		routineFinishDTO.setRemainingTime(remainingTime);

		return routineFinishDTO;
	}

	private String toString(int time) {
		int temp = time < 0 ? time * -1 : time;
		int hou = temp/60/60;
		int min = temp/60%60;
		int sec = temp%60;
		return (time < 0 ? "-" : "")
				+ (hou == 0 ? "" : hou + "시")
				+ (min == 0 ? (hou == 0 ? "" : "00분" ) : (
					min > 10 ? min : (hou == 0 ? min : "0" + min)) + "분")
				+ (sec >= 10 ? sec : (hou == 0 && min == 0 ? sec : "0" + sec)) + "초";
	}
}
