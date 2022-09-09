package com.soo.routine.service;

import com.soo.routine.mapper.MissionMapper;
import com.soo.routine.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper;
    private final ModelMapper modelMapper;

}
