package com.soo.routine.service.mission;

import com.soo.routine.dto.mission.MissionIconDTO;
import com.soo.routine.entity.mission.MissionIcon;
import com.soo.routine.repository.mission.MissionIconRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MissionIconService {

	private final MissionIconRepository missionIconRepository;
	private final ModelMapper modelMapper;

	public List<MissionIconDTO> getIconList() {

		List<MissionIcon> list = missionIconRepository.findAll();
		Type type = new TypeToken<List<MissionIconDTO>>() {}.getType();

		TypeMap<MissionIcon, MissionIconDTO> typeMap = modelMapper.typeMap(MissionIcon.class, MissionIconDTO.class);
		typeMap.addMapping(MissionIcon::getId, MissionIconDTO::setMissionIconId);

		return modelMapper.map(list, type);
	}
}
