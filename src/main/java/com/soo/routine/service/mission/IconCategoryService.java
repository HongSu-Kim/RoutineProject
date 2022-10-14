package com.soo.routine.service.mission;

import com.soo.routine.dto.mission.IconCategoryDTO;
import com.soo.routine.repository.mission.IconCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class IconCategoryService {

	private final IconCategoryRepository iconCategoryRepository;

	public List<IconCategoryDTO> getCategoryList() {
		return iconCategoryRepository.findAll()
				.stream().map(IconCategoryDTO::new)
				.collect(Collectors.toList());
	}
}
