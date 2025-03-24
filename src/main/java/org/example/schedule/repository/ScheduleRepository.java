package org.example.schedule.repository;

import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAll();

    Optional<Schedule> findById(Long id);

    Schedule findByIdOrElseThrows(Long id);

    int updateSchedule(Long id, String todo, String username, String password);

    int delete(Long id);
}
