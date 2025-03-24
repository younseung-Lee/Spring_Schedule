package org.example.schedule.controller;

import org.example.schedule.dto.ScheduleRequesDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 1. 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequesDto dto){
        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.OK);
    }
    // 2. 전체 일정 조회
    // 3. 선택 일정 조회
    // 4. 선택한 일정 수정
    // 5. 선택한 일정 삭제

}
