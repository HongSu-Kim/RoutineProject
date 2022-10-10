package com.soo.routine.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper ModelMapperConfig() {
		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration()
//				.setFieldMatchingEnabled(true)
//				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
		return modelMapper;
    }

}
