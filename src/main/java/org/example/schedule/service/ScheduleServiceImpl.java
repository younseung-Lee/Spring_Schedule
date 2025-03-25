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
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        if (dto.getTodo() == null || dto.getUsername() == null || dto.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "입력 값이 유효하지 않습니다. id = " + id);
        }

        // 기존 데이터 가져오기 (null 체크 추가)
        Schedule schedule = scheduleRepository.findByIdOrElseThrows(id);
        if (schedule == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 데이터가 존재하지 않습니다. id = " + id);
        }
        // 현재 저장된 비밀번호 로그 출력
        System.out.println("schedule.getPassword(): " + schedule.getPassword());
        System.out.println("dto.getPassword(): " + dto.getPassword());

        // 비밀번호 검증 (schedule.getPassword()가 null이면 예외 발생)
        if (schedule.getPassword() == null || !schedule.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다. id = " + id);
        }

        // 업데이트된 정보 적용 (비밀번호는 기존 값 유지)
        schedule.updateTodo(dto.getTodo(), dto.getUsername(), schedule.getPassword());

        // DB 업데이트 수행 (비밀번호 제외)
        int updateRow = scheduleRepository.updateSchedule(
                id,
                schedule.getTodo(),
                schedule.getUsername(),
                schedule.getPassword(), // 기존 비밀번호 유지
                schedule.getUpdatetime()
        );

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 데이터가 존재하지 않습니다. id = " + id);
        }

        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public void delete(Long id, String password) {
        // ID에 해당하는 스케줄을 찾기
        Schedule schedule = scheduleRepository.findByIdOrElseThrows(id);
        // 비밀번호 검증 (schedule.getPassword()가 null이면 예외 발생)
        if (schedule.getPassword() == null || !schedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다. id = " + id);
        }
        // 비밀번호가 일치하면 삭제
        int deleteRow = scheduleRepository.delete(id);

        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id의 데이터가 존재하지 않습니다. id = " + id);
        }
    }
}
