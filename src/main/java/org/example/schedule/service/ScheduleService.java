package org.example.schedule.service;


import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAll();

    ScheduleResponseDto findById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String todo, String username, String password);

    void delete(Long id);
}
