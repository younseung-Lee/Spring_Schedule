package org.example.schedule.service;


import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAll();

    ScheduleResponseDto findById(Long id);

    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto);

    void delete(Long id, String password);
}
