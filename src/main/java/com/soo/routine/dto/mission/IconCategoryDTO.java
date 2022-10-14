package com.soo.routine.dto.mission;

import com.soo.routine.entity.mission.IconCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IconCategoryDTO {

	private Long iconCategoryId;
	private String categoryName;
	private String iconPath;
	private Boolean pay;

	public IconCategoryDTO(IconCategory iconCategory) {
		iconCategoryId = iconCategory.getId();
		categoryName = iconCategory.getCategoryName();
		iconPath = iconCategory.getIconPath();
		pay = iconCategory.getPay();
	}
}
