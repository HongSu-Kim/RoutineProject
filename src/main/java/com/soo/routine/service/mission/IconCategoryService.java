package com.soo.routine.service.mission;

import com.soo.routine.dto.mission.IconCategoryDTO;
import com.soo.routine.entity.mission.IconCategory;
import com.soo.routine.repository.mission.IconCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IconCategoryService {

	private final IconCategoryRepository iconCategoryRepository;
	private final ModelMapper modelMapper;

	public List<IconCategoryDTO> getCategoryList() {

		List<IconCategory> list = iconCategoryRepository.findAll();
		Type type = new TypeToken<List<IconCategoryDTO>>() {}.getType();

		TypeMap<IconCategory, IconCategoryDTO> typeMap = modelMapper.typeMap(IconCategory.class, IconCategoryDTO.class);
		typeMap.addMapping(IconCategory::getId, IconCategoryDTO::setIconCategoryId);

		return modelMapper.map(list, type);
	}
}
