package org.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedule.entity.Schedule;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String username;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.username = schedule.getUsername();
        this.createtime = schedule.getCreatetime();
        this.updatetime = schedule.getUpdatetime();
    }
}
