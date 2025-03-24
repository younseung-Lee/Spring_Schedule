package org.example.schedule.service;


import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;


    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }



    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getTodo(), dto.getUsername(), dto.getPassword());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAll() {
        List<ScheduleResponseDto> allSchedule = scheduleRepository.findAll();
        return allSchedule;
    }

    @Override
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrows(id);
        return new ScheduleResponseDto(schedule);

    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String todo, String username, String password) {
        if (todo == null || username == null || password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "입력 값이 유효하지 않습니다. id = " + id);
        }

        // 기존 데이터 가져오기
        Schedule schedule = scheduleRepository.findByIdOrElseThrows(id);

        // 업데이트된 정보 적용 (createtime은 유지하고 updatetime만 변경)
        schedule.updateTodo(todo, username, password);

        // DB 업데이트 수행
        int updateRow = scheduleRepository.updateSchedule(id, schedule.getTodo(), schedule.getUsername(), schedule.getPassword());

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 데이터가 존재하지 않습니다. id = " + id);
        }

        // 업데이트된 데이터를 다시 조회
        Schedule updatedSchedule = scheduleRepository.findByIdOrElseThrows(id);

        return new ScheduleResponseDto(updatedSchedule);
    }

    @Override
    public void delete(Long id) {
        int deleteRow = scheduleRepository.delete(id);
        if (deleteRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id값이 없습니다 = " + id );
        }

    }
}
